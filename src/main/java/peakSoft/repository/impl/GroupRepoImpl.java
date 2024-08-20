package peakSoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import peakSoft.entity.Course;
import peakSoft.entity.Group;
import peakSoft.exception.MyException;
import peakSoft.repository.GroupRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
@RequiredArgsConstructor
public class GroupRepoImpl implements GroupRepo {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public void saveGroup(Group group) {
        if (group.getId()==null){
            entityManager.persist(group);
        }else entityManager.merge(group);
    }

    @Override
    public List<Group> getAllGroups() {
        return  entityManager.createQuery("select g from Group g ", Group.class)
                .getResultList();
    }

    @Override
    public Group getById(Long id) {
        try{
            Group group = entityManager.find(Group.class, id);
            if (group==null){
                throw new MyException("Not found");
            } else return group;
        }catch (MyException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public void updateGroup(Long id, Group newGroup) {
        try{
            Group group = entityManager.find(Group.class, id);
            if (group==null){
                throw new MyException("Not found Group");
            }else {
                group.setGroupName(newGroup.getGroupName());
                group.setDescription(newGroup.getDescription());
                group.setImageLink(newGroup.getImageLink());
            }
        }catch (MyException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteGroup(Long id) {

            Group group = entityManager.find(Group.class, id);
            entityManager.createQuery("delete from Group g where g.id=?1")
                    .setParameter(1,group.getId()).executeUpdate();

    }

    @Override
    public void saveGroupToCourse(Long courseId, Group group) {

        Set<Group>groups= new HashSet<>();
        groups.add(group);
        try{
            List<Course> course = entityManager.createQuery("select c from Course c where c.id=:courseId", Course.class)
                    .setParameter("courseId", courseId)
                    .getResultList();
            if (course.isEmpty()){
                throw new MyException("Not found Course");
            }else {
                course.get(0).setGroups(groups);
                entityManager.persist(group);
            }

        }catch (MyException e){
            System.out.println(e.getMessage());
        }

    }
}
