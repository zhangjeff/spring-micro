package com.jeff.util;

import org.springframework.core.io.Resource;

public interface ContextResource extends Resource {

	String getPathWithinContext();

}
