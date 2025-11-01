package com.example.wagonmanager.service;

import com.example.wagonmanager.dto.AuditEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.*;


@Service
public class AuditService {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AuditService(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private RowMapper<AuditEntry> auditRowMapper = new RowMapper<AuditEntry>() {
        @Override
        public AuditEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
            AuditEntry e = new AuditEntry();
            e.setId(rs.getLong("id"));
            try { e.setEntity(rs.getString("entity")); } catch (SQLException ignored) {}
            try { e.setEntityUuid(rs.getString("entity_uuid")); } catch (SQLException ignored) {}
            try { e.setAction(rs.getString("action")); } catch (SQLException ignored) {}
            try { e.setChangedBy(rs.getString("changed_by")); } catch (SQLException ignored) {}
            try {
                java.sql.Timestamp ts = rs.getTimestamp("changed_at");
                if (ts != null) e.setChangedAt(ts.toInstant().atOffset(OffsetDateTime.now().getOffset()));
            } catch (SQLException ignored) {}
            // map JSON column 'changes' to Map (this uses JDBC driver's getObject — for PG returns PGObject with JSON)
            try {
                String json = rs.getString("changes");
                if (json != null && !json.isEmpty()) {
                    // simple JSON -> Map parsing using Jackson would be nicer; try naive parsing via util
                    com.fasterxml.jackson.databind.ObjectMapper om = new com.fasterxml.jackson.databind.ObjectMapper();
                    Map<String,Object> m = om.readValue(json, Map.class);
                    e.setChanges(m);
                }
            } catch (Exception ex) {
                // ignore mapping errors; set changes null
            }
            return e;
        }
    };

    /**
     * Query audit entries by table name (without schema) and entity uuid with pagination.
     *
     * @param auditTableName e.g. "wagon_audit" or "inventory_item_audit"
     */
    public List<AuditEntry> queryAuditTable(String auditTableName, String entityUuid, Optional<OffsetDateTime> since, int page, int size) {
        String base = "SELECT id, entity, entity_uuid, action, changed_by, changed_at, changes FROM " + auditTableName +
                " WHERE entity_uuid = ? ";
        List<Object> params = new ArrayList<>();
        params.add(entityUuid);

        if (since.isPresent()) {
            base += " AND changed_at >= ? ";
            params.add(java.sql.Timestamp.from(since.get().toInstant()));
        }

        base += " ORDER BY changed_at DESC LIMIT ? OFFSET ? ";
        params.add(size);
        params.add(page * size);

        return jdbcTemplate.query(base, params.toArray(), auditRowMapper);
    }

    /**
     * Count entries in audit table for pagination.
     */
    public long countAuditTable(String auditTableName, String entityUuid, Optional<OffsetDateTime> since) {
        String base = "SELECT count(*) FROM " + auditTableName + " WHERE entity_uuid = ? ";
        List<Object> params = new ArrayList<>();
        params.add(entityUuid);
        if (since.isPresent()) {
            base += " AND changed_at >= ? ";
            params.add(java.sql.Timestamp.from(since.get().toInstant()));
        }
        return jdbcTemplate.queryForObject(base, params.toArray(), Long.class);
    }
}
