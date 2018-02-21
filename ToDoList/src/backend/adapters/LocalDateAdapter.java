package backend.adapters;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate>{

	@Override
	public String marshal(LocalDate arg0) throws Exception {
		// TODO Auto-generated method stub
		return arg0.toString();
	}

	@Override
	public LocalDate unmarshal(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return LocalDate.parse(arg0);
	}

}
