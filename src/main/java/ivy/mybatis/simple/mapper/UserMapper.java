package ivy.mybatis.simple.mapper;

import ivy.mybatis.simple.model.SysRole;
import ivy.mybatis.simple.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    SysUser selectById(Long id);
    List<SysUser> selectAll();

    List<SysRole> selectRolesByUserId(Long userId);

    int insert(SysUser sysUser);
    int insert2(SysUser sysUser);
    int insert3(SysUser sysUser);

    int updateById(SysUser sysUser);
    int updateByIdSelective(SysUser sysUser);

    int deleteById(SysUser sysUser);
    int deleteById(Long id);

    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId, @Param("enabled") Integer enabled);

    List<SysUser> selectByUser(SysUser user);

    SysUser selectByIdOrName(SysUser user);

    List<SysUser> selectByIdList(List<Long> ids);

    int insertList(List<SysUser> userList);

    int updateByMap(Map<String, Object> map);

    SysUser selectUserAndRoleById(Long id);
    SysUser selectUserAndRoleById2(Long id);
    SysUser selectUserAndRoleByIdSelect(Long id);

    List<SysUser> selectAllUserAndRoles();

}
