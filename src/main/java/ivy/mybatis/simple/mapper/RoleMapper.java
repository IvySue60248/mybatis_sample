package ivy.mybatis.simple.mapper;

import ivy.mybatis.simple.model.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@CacheNamespaceRef(RoleMapper.class)
public interface RoleMapper {

    @Select({"select id, role_name roleName, enabled, create_by createBy, create_time createTime",
    "from sys_role",
    "where id = #{id}"})
    SysRole selectById(Long id);

    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select({"select *",
            "from sys_role",
            "where id = #{id}"})
    SysRole selectById2(Long id);


    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })

    @ResultMap("roleResultMap")
    @Select({"select *",
            "from sys_role"})
    List<SysRole> selectAll();

    List<SysRole> selectAllRoleAndPrivileges();

    List<SysRole> selectRoleByUserId(Long userId);

    List<SysRole> selectRoleByUserIdChoose(Long userId);

    @Insert({ "insert into sys_role (role_name, enabled, create_by, create_time)",
            "values(#{roleName}, #{enabled}, #{createBy},#{createTime, jdbcType=TIMESTAMP})"})
    int insert(SysRole sysRole);

    @Insert({ "insert into sys_role (role_name, enabled, create_by, create_time)",
            "values(#{roleName}, #{enabled}, #{createBy},#{createTime, jdbcType=TIMESTAMP})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert2(SysRole sysRole);

    @Insert({ "insert into sys_role (role_name, enabled, create_by, create_time)",
            "values(#{roleName}, #{enabled}, #{createBy},#{createTime, jdbcType=TIMESTAMP})"})
    @SelectKey(statement = "select LAST_INSERT_ID()",
            keyProperty = "id",
            resultType = Long.class,
            before = false)
    int insert3(SysRole sysRole);

    @Update({"update sys_role set role_name=#{roleName}, enabled=#{enabled}, create_by=#{createInfo.createBy}, create_time=#{createInfo.createTime} where id = #{id}"})
    int updateById(SysRole sysRole);

    @Delete({"delete from sys_role where id = #{id}"})
    int deleteById(SysRole sysRole);

}
