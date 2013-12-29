package github.jackyliao123.algebra;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * This class stores the coefficient for each unknown number
 * @author jackyliao123
 */

public class Num implements Serializable{
	private static final long serialVersionUID = 3495248830884808813L;
	char unknownChars[];
	Rational number;
	Rational[] unknown;
	public Num(Rational num, Rational[] unknown, char[] chars){
		unknownChars = chars;
		this.unknown = new Rational[unknownChars.length];
		this.number = num;
		this.unknown = unknown;
	}
	public Num(Rational num, char[] chars){
		unknownChars = chars;
		unknown = new Rational[unknownChars.length];
		number = num;
		for(int i = 0; i < unknownChars.length; i ++){
			unknown[i] = new Rational(new BigInteger("0"), new BigInteger("1"));
		}
	}
	public Num(Rational num, Rational unk, char unkchar, char[] chars){
		unknownChars = chars;
		unknown = new Rational[unknownChars.length];
		number = num;
		for(int i = 0; i < unknownChars.length; i ++){
			if(unknownChars[i] == unkchar)
				unknown[i] = unk;
			else
				unknown[i] = new Rational(new BigInteger("0"), new BigInteger("1"));
		}
	}
	public Num(Rational unk, char unkchar, char[] chars){
		unknownChars = chars;
		unknown = new Rational[unknownChars.length];
		number = new Rational(new BigInteger("0"), new BigInteger("1"));
		for(int i = 0; i < unknownChars.length; i ++){
			if(unknownChars[i] == unkchar)
				unknown[i] = unk;
			else
				unknown[i] = new Rational(new BigInteger("0"), new BigInteger("1"));
		}
	}
	public Num add(Num num){
		Rational[] unk = new Rational[unknown.length];
		for(int i = 0; i < unknown.length; i ++){
			unk[i] = unknown[i].add(num.unknown[i]);
		}
		return new Num(number.add(num.number), unk, unknownChars);
	}
	public Num subtract(Num num){
		Rational[] unk = new Rational[unknown.length];
		for(int i = 0; i < unknown.length; i ++){
			unk[i] = unknown[i].subtract(num.unknown[i]);
		}
		return new Num(number.subtract(num.number), unk, unknownChars);
	}
	public Num multiply(Num num){
		if(num.isPureNumber()){
			Rational[] unk = new Rational[unknown.length];
			for(int i = 0; i < unknown.length; i ++){
				unk[i] = unknown[i].multiply(num.number);
			}
			return new Num(number.multiply(num.number), unk, unknownChars);
		}
		else if(isPureNumber()){
			Rational[] unk = new Rational[unknown.length];
			for(int i = 0; i < unknown.length; i ++){
				unk[i] = num.unknown[i].multiply(number);
			}
			return new Num(num.number.multiply(number), unk, unknownChars);
		}
		else{
			throw new ArithmeticException("Not a valid linear equation");
		}
	}
	public Num divide(Num num){
		if(!num.isPureNumber()){
			throw new ArithmeticException("Not a valid linear equation");
		}
		Rational[] unk = new Rational[unknown.length];
		for(int i = 0; i < unknown.length; i ++){
			unk[i] = unknown[i].divide(num.number);
		}
		return new Num(number.divide(num.number), unk, unknownChars);
	}
	public boolean isPureNumber(){
		for(int i = 0; i < unknown.length; i ++){
			if(!unknown[i].equals(0)){
				return false;
			}
		}
		return true;
	}
	public String toString(){
		String s = "" + number;
		for(int i = 0; i < unknown.length; i ++){
			s += " + " + unknown[i] + unknownChars[i];
		}
		return s;
	}
}