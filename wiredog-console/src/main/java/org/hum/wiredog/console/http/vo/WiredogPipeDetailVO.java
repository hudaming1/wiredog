package org.hum.wiredog.console.http.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class WiredogPipeDetailVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Map<String, String>> pipeEvent;
}
