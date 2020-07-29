package com.empresa.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	
	public static String decodeParam(String text) {
		//método para habilitar a pesquisa, pois pode-se ter problemas com caracteres especiais como o " "
		//nesse caso cada vai se retornar uma string com cada caractere especial decodificado já que cada caractere especial virá codificado
		//tipo, espaço é representado por %20
		
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}	
}
