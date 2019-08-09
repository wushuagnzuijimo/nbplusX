package com.woniu.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woniu.util.JdbcUtils;

public class BaseDAO<K> {
	
	Connection conn;
	PreparedStatement stat;
	ResultSet rs;
	
	
	
	public void update(String sql,Object[]objs) {
		
		try {
			conn =JdbcUtils.getConn();
			stat=conn.prepareStatement(sql);
			for(int i=0;i<objs.length;i++) {
				stat.setObject(i+1, objs[i]);
				
			}
			int rows=stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtils.closeConn(rs, stat, conn);
			
		}
		
	}
	public List<K> select(String sql,Object[] objs,Class c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		List<K> list = new ArrayList<K>();
		
		try {
			conn=JdbcUtils.getConn();
			stat=conn.prepareStatement(sql);
			for(int i=0;i<objs.length;i++) {
				stat.setObject(i+1, objs[i]);
				
				
				
			}
			rs=stat.executeQuery();
			while(rs.next()) {
				Object obj =c.newInstance();
				//找到所有的set方法
				Method [] ms=c.getDeclaredMethods();
				for(Method m:ms) {
					String methodname = m.getName();
					if(methodname.startsWith("set")) {
						//获得列名
						String fieldname = methodname.substring(3);
						m.invoke(obj, rs.getObject(fieldname));
						
						
					}
					
				}
				list.add((K) obj);
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
		
		
	}
	
	
	
	

}
