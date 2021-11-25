package com.betacomp.exceptions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//simple error details class
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CustomErrorDetails {
	
	private Date timestamp ;
	private String message ;
	private String errorDetails ; 
	

}
