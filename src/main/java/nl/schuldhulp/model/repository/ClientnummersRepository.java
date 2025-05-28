package nl.schuldhulp.model.repository;

import nl.schuldhulp.model.classes.Clientnummers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientnummersRepository extends JpaRepository<Clientnummers, String> {

}
