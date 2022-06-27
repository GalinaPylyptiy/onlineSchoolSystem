package action;

import dao.TeacherDAO;
import daoImpl.TeacherDAOImpl;
import entity.Teacher;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeleteTeacherRecordAction implements Action {

    private TeacherDAO teacherDAO = new TeacherDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long teacherId = Long.parseLong(request.getParameter(TEACHER_ID));
        teacherDAO.deleteTeacher(teacherId);
        HttpSession session = request.getSession();
        List<Teacher> teacherList = teacherDAO.getAllTeachers();
        session.setAttribute(TEACHER_LIST, teacherList);
        request.getRequestDispatcher(TEACHER_LIST_JSP).forward(request, response);

    }
}
