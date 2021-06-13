package test.dao;

public class DAOFactory {
	
	public static StudentDAO getStudentDAOInstance()
    {
        return new StudentDAOImpl();
    }
	
	public static TeacherDAO getTeacherDAOInstance()
    {
        return new TeacherDAOImpl();
    }
	
	public static AdminDAO getAdminDAOInstance()
    {
        return new AdminDAOImpl();
    }

	public static ExamDAO getExamDAOInstance() {
		return new ExamDAOImpl();
	}
}