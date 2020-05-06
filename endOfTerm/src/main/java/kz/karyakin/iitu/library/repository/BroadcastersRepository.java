package kz.karyakin.iitu.library.repository;

import kz.karyakin.iitu.library.entity.Broadcasters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BroadcastersRepository extends JpaRepository<Broadcasters, Long> {

}
