package plumbeer.dev.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import plumbeer.dev.domain.Mensaje;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Mensaje entity.
 */
public interface MensajeRepository extends JpaRepository<Mensaje,Long> {

    @Query("select mensaje from Mensaje mensaje where mensaje.emisor.login = ?#{principal.username}")
    Page<Mensaje> findByEmisorIsCurrentUser(Pageable pageable);

    @Query("select mensaje from Mensaje mensaje where mensaje.receptor.login = ?#{principal.username}")
    Page<Mensaje> findByReceptorIsCurrentUser(Pageable pageable);

    @Query("select count(id) from Mensaje mensaje where leido = false and mensaje.receptor.login = ?#{principal.username}")
    Integer findUnreadMensaje();

}
