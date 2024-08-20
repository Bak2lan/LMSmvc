package peakSoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peakSoft.entity.Group;
import peakSoft.repository.GroupRepo;
import peakSoft.service.GroupService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepo groupRepo;
    @Override
    public void saveGroup(Group group) {
        groupRepo.saveGroup(group);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepo.getAllGroups();
    }

    @Override
    public Group getById(Long id) {
        return groupRepo.getById(id);
    }

    @Override
    public void updateGroup(Long id, Group newGroup) {
        groupRepo.updateGroup(id,newGroup);
    }

    @Override
    public void deleteGroup(Long id) {
            groupRepo.deleteGroup(id);
    }

    @Override
    public void saveGroupToCourse(Long courseId, Group group) {
        groupRepo.saveGroupToCourse(courseId,group);
    }
}
