package org.workspace7.mybatis2.application;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PersonMapper {

	@Select("SELECT * FROM PERSONS where id = #{id}")
	@Results({
		@Result(property="firstName",column="first_name"),
		@Result(property="lastName",column="last_name")
	})
	Person selectPerson(long id) throws SQLException;
}
