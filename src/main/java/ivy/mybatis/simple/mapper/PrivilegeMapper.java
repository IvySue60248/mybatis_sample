package ivy.mybatis.simple.mapper;

import ivy.mybatis.simple.model.SysPrivilege;

import java.util.List;

public interface PrivilegeMapper {

    List<SysPrivilege> selectPrivilegeByRoleId(Long roleId);
}
