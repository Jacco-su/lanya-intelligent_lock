/*
 *  Copyright (c) 2016-2020  陶乐乐,毛磊版权所有 
 *  Tao Lele, Pierre Morel. All Rights Reserved.
 */

package com.dream.util;

import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/*
 * 使用Wrapper,重写HttpServletRequestWrapper
 * @winx.xin
 * 20150522
*/
@SuppressWarnings("unchecked")  
public class ParameterRequestWrapper extends HttpServletRequestWrapper {
    private Map params;
    public ParameterRequestWrapper(HttpServletRequest request, Map newParams) {  
        super(request);  
        this.params = newParams;  
    }
    public Map getParameterMap() {  
        return params;  
    }
    public Enumeration getParameterNames() {  
        Vector l = new Vector(params.keySet());  
        return l.elements();  
    }
    public String[] getParameterValues(String name) {  
        Object v = params.get(name);  
        if (v == null) {  
            return null;  
        } else if (v instanceof String[]) {  
            return (String[]) v;  
        } else if (v instanceof String) {  
            return new String[] { (String) v };  
        } else {  
            return new String[] { v.toString() };  
        }  
    }
    public String getParameter(String name) {  
        Object v = params.get(name);  
        if (v == null) {  
            return null;  
        } else if (v instanceof String[]) {  
            String[] strArr = (String[]) v;  
            if (strArr.length > 0) {  
                return strArr[0];  
            } else {  
                return null;  
            }  
        } else if (v instanceof String) {  
            return (String) v;  
        } else {  
            return v.toString();  
        }  
    }  
}  
