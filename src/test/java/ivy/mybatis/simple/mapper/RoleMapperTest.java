package ivy.mybatis.simple.mapper;

import ivy.mybatis.simple.model.CreateInfo;
import ivy.mybatis.simple.model.SysPrivilege;
import ivy.mybatis.simple.model.SysRole;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class RoleMapperTest extends BaseMapperTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(1l);
            Assert.assertNotNull(role);
            Assert.assertEquals("管理员", role.getRoleName());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById2() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById2(1l);
            Assert.assertNotNull(role);
            Assert.assertEquals("管理员", role.getRoleName());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList = roleMapper.selectAll();
            Assert.assertNotNull(roleList);
            Assert.assertEquals(2, roleList.size());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("PO");
            sysRole.setEnabled(1);
            CreateInfo createInfo = new CreateInfo();
            createInfo.setCreateBy(1l);
            createInfo.setCreateTime(new Date());
            sysRole.setCreateInfo(createInfo);
            int result = roleMapper.insert(sysRole);
            Assert.assertEquals(1, result);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("PO");
            sysRole.setEnabled(1);
            CreateInfo createInfo = new CreateInfo();
            createInfo.setCreateBy(1l);
            createInfo.setCreateTime(new Date());
            sysRole.setCreateInfo(createInfo);
            int result = roleMapper.insert2(sysRole);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(sysRole.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("PO");
            sysRole.setEnabled(1);
            CreateInfo createInfo = new CreateInfo();
            createInfo.setCreateBy(1l);
            createInfo.setCreateTime(new Date());
            sysRole.setCreateInfo(createInfo);
            int result = roleMapper.insert3(sysRole);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(sysRole.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(1l);
            CreateInfo createInfo = new CreateInfo();
            createInfo.setCreateBy(1001l);
            role.setCreateInfo(createInfo);
            int result = roleMapper.updateById(role);
            Assert.assertEquals(1, result);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(1l);
            int result = roleMapper.deleteById(role);
            Assert.assertEquals(1, result);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllRoleAndPrivileges() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roles = roleMapper.selectAllRoleAndPrivileges();
            for(SysRole role: roles) {
                System.out.println("role name:" + role.getRoleName());
                for (SysPrivilege privilege: role.getPrivilegeList()) {
                    System.out.println("privilege name:" + privilege.getPrivilegeName());
                }
            }
        } finally {
            sqlSession.close();
        }
    }
}
