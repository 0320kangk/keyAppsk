package project.keyappsk.domain.alarm.repository.alarmRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.keyappsk.domain.alarm.entity.AlarmMessage;

import java.util.List;

@Repository
public interface AlarmMessageRepository extends JpaRepository<AlarmMessage, Integer> {
    Slice<AlarmMessage> findByReceiver_Id (Integer id, Pageable pageable);

    List<AlarmMessage> findByReceiver_Id (Integer id);

}
