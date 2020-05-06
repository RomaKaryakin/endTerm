package kz.karyakin.iitu.library.repository;

import kz.karyakin.iitu.library.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(kz.karyakin.iitu.library.enums.Role name);

}
