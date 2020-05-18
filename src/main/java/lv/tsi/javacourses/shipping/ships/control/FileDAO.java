package lv.tsi.javacourses.shipping.ships.control;

import lv.tsi.javacourses.shipping.ships.model.FileEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FileDAO {
    private static final Logger log = LoggerFactory.getLogger(FileDAO.class);

    @PersistenceContext
    private EntityManager em;

    public FileEntity selectFileById(long id) {
        log.info("selectFileById method called with ID {}",id);
        return em.find(FileEntity.class, id);
    }

    public void save(FileEntity fileEntity) {
        if (fileEntity.getId() == null) {
            em.persist(fileEntity);
        } else {
            em.merge(fileEntity);
        }
    }
}
