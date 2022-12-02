package es.capgemini.cca.canbemini.permission;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permission, Long> {
    List<Permission> findAllByOrderByIdAsc();
}
