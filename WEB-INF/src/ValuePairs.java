import java.util.*;

public class ValuePairs<T>{

	private String identifier;
	private T value;

	public ValuePairs(String identifier, T value){
		this.identifier = identifier;
		this.value = value;
	}

	public ValuePairs(){}

	public String getID(){
		return identifier;
	}
	public T getValue(){
		return value;
	}
	public void setID(String identifier){
		this.identifier = identifier;
	}
	public void setValue(T value){
		this.value = value;
	}
}
