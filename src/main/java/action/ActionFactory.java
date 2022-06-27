package action;

import java.util.HashMap;
import java.util.Map;
import static constants.RequestConstants.*;

public class ActionFactory {
    private final static Map<String, Action> ACTION_MAP = new HashMap<>();
    static {
        ACTION_MAP.put(TEACHER_REGISTER, new TeacherRegisterAction() );
        ACTION_MAP.put(STUDENT_REGISTER, new StudentRegisterAction());
        ACTION_MAP.put(TEACHER_LOGIN, new TeacherLoginAction());
        ACTION_MAP.put(STUDENT_LOGIN, new StudentLoginAction());
        ACTION_MAP.put(ERROR, new ErrorAction());
        ACTION_MAP.put(ADD_SCHEDULE_RECORD, new AddScheduleRecordAction());
        ACTION_MAP.put(ATTRIBUTES_FOR_SCHEDULE_RECORD, new SetAttributeForScheduleRecordAction());
        ACTION_MAP.put(ATTRIBUTES_FOR_TEACHER_REGISTER, new SetAttributeForTeacherRegisterAction());
        ACTION_MAP.put(ATTRIBUTES_FOR_STUDENT_REGISTER, new SetAttributeForStudentRegisterAction());
        ACTION_MAP.put(ATTRIBUTES_FOR_CURRICULUM_RECORD, new SetAttributeForCurriculumRecordAction());
        ACTION_MAP.put(ATTRIBUTES_FOR_ASSESSMENT_RECORD, new SetAttributeForAssessmentRecordAction());
        ACTION_MAP.put(ADD_CURRICULUM_RECORD, new AddCurriculumRecordAction());
        ACTION_MAP.put(ADD_ASSESSMENT_RECORD, new AddAssessmentRecordAction());
        ACTION_MAP.put(GET_STUDENT_LIST_FOR_ASSESSMENT, new GetStudentAndCurriculumListForAssessment());
        ACTION_MAP.put(GET_SUBJECT_LIST, new GetSubjectListAttributeForStudentGrade());
        ACTION_MAP.put(GET_STUDENTS_GRADES, new GetStudentsGradesAction());
        ACTION_MAP.put(ATTRIBUTES_FOR_HOME_TASK, new GetAttributesForHomeTaskWatching());
        ACTION_MAP.put(WATCH_HOME_TASK, new WatchHomeTaskAction());
        ACTION_MAP.put(UPDATE_CURRICULUM_RECORD, new UpdateCurriculumRecordAction());
        ACTION_MAP.put(GET_LOCALE_LIST,new GetLocaleListAction());
        ACTION_MAP.put(ATTRIBUTES_FOR_TEACHER_SCHEDULE,new GetAttributeForTeacherSchedule());
        ACTION_MAP.put(ATTRIBUTES_FOR_STUDENT_SCHEDULE, new GetAttributeForStudentSchedule());
        ACTION_MAP.put(DELETE_CURRICULUM_RECORD, new DeleteCurriculumRecordAction());
        ACTION_MAP.put(DELETE_ASSESSMENT_RECORD, new DeleteAssessmentRecordAction());
        ACTION_MAP.put(DELETE_SCHEDULE_RECORD, new DeleteScheduleRecordAction());
        ACTION_MAP.put(DELETE_TEACHER_RECORD, new DeleteTeacherRecordAction());
        ACTION_MAP.put(DELETE_STUDENT_RECORD, new DeleteStudentRecordAction());


    }
    public static ActionFactory getInstance(){
        return new ActionFactory();
    }
    public Action getAction(String requestString){
        Action action = ACTION_MAP.get(ERROR);
        for(Map.Entry<String, Action> entry: ACTION_MAP.entrySet()){
            if(requestString.equalsIgnoreCase(entry.getKey())){
                action =  entry.getValue();
            }
        }
        return action;
    }
}
