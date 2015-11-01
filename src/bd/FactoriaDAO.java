package bd;

public class FactoriaDAO {
	
	private static FactoriaDAO factoria = null;
	
	private FactoriaDAO(){}
	
	public static FactoriaDAO getFactoriaDAO(){
		if(factoria ==  null){factoria = new FactoriaDAO();}
		return factoria;
	}
	
	public static IDAO<Child> getChildDAO(){
		return ChildDAO.getChildDAO();
	}

	
}
