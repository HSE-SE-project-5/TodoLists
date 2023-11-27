package hse.todolist.databaseInteractor;

import jakarta.persistence.*;

import hse.todolist.entities.List;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static java.lang.Math.min;

@Service
public class ListService {
    private final EntityManagerFactory
            entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    private final Query getMaxListId = entityManager.createNativeQuery
            ("SELECT MAX(list_id) from list");

    @SuppressWarnings("unchecked")
    private final TypedQuery<List> getListWithListIdsQuery =
            (TypedQuery<List>) entityManager.createNativeQuery
                    ("SELECT * FROM list where list_id IN :track_id_array", List.class);

    @SuppressWarnings("unchecked")
    private final TypedQuery<List> getAllListsQuery =
            (TypedQuery<List>) entityManager.createNativeQuery
                    ("SELECT * FROM list", List.class);

    /**
     * Given List of list_id's, finds matching records with corresponding list_id's
     * in List database table.
     *
     * @return List of List objects, if at least one list_id from given List was matched successfully,
     * and null, if no matches were found.
     */
    public synchronized java.util.List<List> getListsWithListIds(java.util.List<Integer> arrayOfTrackIds) {
        EntityTransaction transaction = entityManager.getTransaction();

        ArrayList<List> array;

        try {
            transaction.begin();

            getListWithListIdsQuery.setParameter("track_id_array", arrayOfTrackIds);
            array = new ArrayList<>(getListWithListIdsQuery.getResultList());

            transaction.commit();

        } catch (jakarta.persistence.NoResultException e) {
            return null;

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return array;
    }

    /**
     * Finds lists in List database table.
     *
     * @return List of List objects, if at least one track is present,
     * and null, if no matches were found.
     */
    public synchronized java.util.List<List> getAllLists() {
        EntityTransaction transaction = entityManager.getTransaction();

        ArrayList<List> array;

        try {
            transaction.begin();

            array = new ArrayList<>(getAllListsQuery.getResultList());

            transaction.commit();

        } catch (jakarta.persistence.NoResultException e) {
            return null;

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return array;
    }



    /**
     * Given a list_id, finds matching record in List database table.
     *
     * @return Object of class List, if matching record was found, and null otherwise.
     */
    public synchronized List getListWithListId(int track_id) {
        EntityTransaction transaction = entityManager.getTransaction();

        List track;

        try {
            transaction.begin();

            track = entityManager.find(List.class, track_id);

            transaction.commit();

        } catch (NoResultException | IllegalArgumentException e) {
            return null;

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return track;
    }

    /**
     * Given name, status, content, adds a new record with given parameters
     * to List database table.
     *
     * @return list_id of new record
     */
    public synchronized int addNewListWithAllParams(String name, String status, String content) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            List track = new List();
            track.setName(name);
            track.setStatus(status);
            track.setContent(content);

            entityManager.merge(track);

            transaction.commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return (int) getMaxListId.getSingleResult();
    }

    /**
     * Given List object, updates parameters in matching record of List database table.
     *
     * @return true, if parameters were updated successfully, and false,
     * if track_id of given List object did not match any of List database table records or
     * given List object is null.
     */
    public synchronized boolean updateListAllParamsWithUpdatedList(List updated_track) {
        if (updated_track == null) {
            return false;
        }

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        List track = entityManager.getReference(List.class, updated_track.getListId());
        if (track == null) {
            return false;
        }

        track.setName(updated_track.getName());
        track.setStatus(updated_track.getStatus());
        track.setContent(updated_track.getContent());

        transaction.commit();

        return true;
    }

    /**
     * Given list_id, deletes matching record of List database table.
     *
     * @return Object of class Track representing the deleted record, if matching record was found, and null otherwise.
     */
    public synchronized List deleteListWithListId(int track_id) {
        List track = getListWithListId(track_id);

        if (track == null) {
            return null;
        }

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.remove(track);

        transaction.commit();

        return track;
    }

    /**
     * Close entity manager and entity manager factory when finished working with class.
     */
    public synchronized void closeHandler() {
        entityManager.close();
        entityManagerFactory.close();
    }
}