package converter;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.financas.modelo.vo.ContaVO;
import br.com.financas.rmi.PersistenciaModelo;

public class ConverterConta implements Converter {

	private Registry registry;
	private PersistenciaModelo persistenciaModelo;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.equals("")) {
			try {
				registry = LocateRegistry.getRegistry("127.0.0.1", 2001);
				persistenciaModelo = (PersistenciaModelo) registry.lookup("OlaServidor");
			} catch (RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
			
			try {
				return persistenciaModelo.consultarPorId(Integer.parseInt(value));
			} catch (NumberFormatException | RemoteException e) {
				e.printStackTrace();
			} 
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (null == value || "".equals(value)) {
		      return null;  
		  }
		return String.valueOf(((ContaVO) value).getId()); 
	}

}
