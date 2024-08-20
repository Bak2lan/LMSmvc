package peakSoft.repository;

import peakSoft.entity.Group;

import java.util.List;

public interface GroupRepo {
    void saveGroup(Group group);
    List<Group> getAllGroups();
    Group getById(Long id);
    void updateGroup(Long id, Group newGroup);
    void deleteGroup(Long id);
    void saveGroupToCourse(Long courseId,Group group);
}
