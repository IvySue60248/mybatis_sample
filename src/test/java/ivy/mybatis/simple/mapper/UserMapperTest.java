package ivy.mybatis.simple.mapper;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import ivy.mybatis.simple.model.SysPrivilege;
import ivy.mybatis.simple.model.SysRole;
import ivy.mybatis.simple.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class UserMapperTest extends BaseMapperTest {
    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = userMapper.selectAll();
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1l);
            Assert.assertNotNull(user);
            Assert.assertEquals("admin", user.getUserName());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectSysRoleByUserId() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roles = userMapper.selectRolesByUserId(1l);
            Assert.assertNotNull(roles);
            Assert.assertEquals(2, roles.size());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> userList = userMapper.selectRolesByUserIdAndRoleEnabled(1l, 1);
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("ivy");
            user.setUserPassword("654321");
            user.setUserEmail("i.xue@sap.com");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            int result = userMapper.insert(user);
            Assert.assertEquals(1, result);
            Assert.assertNull(user.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("ivy");
            user.setUserPassword("654321");
            user.setUserEmail("i.xue@sap.com");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            int result = userMapper.insert2(user);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(user.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2Selective() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test_selective");
            user.setUserPassword("654321");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            int result = userMapper.insert2(user);
            Assert.assertEquals(1, result);
            user = userMapper.selectById(user.getId());
            Assert.assertEquals("test@mybaitis.tk", user.getUserEmail());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("ivy");
            user.setUserPassword("654321");
            user.setUserEmail("i.xue@sap.com");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            int result = userMapper.insert3(user);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(user.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1l);
            Assert.assertEquals("admin", user.getUserName());
            user.setUserName("admin_test");
            user.setUserEmail("test@mybatis.ivy");
            int result = userMapper.updateById(user);
            Assert.assertEquals(1, result);
            user = userMapper.selectById(1L);
            Assert.assertEquals("admin_test", user.getUserName());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByIdSelective() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1l);
            user.setUserEmail("ivy.test@mybaitis.com");
            int result = userMapper.updateByIdSelective(user);
            Assert.assertEquals(1, result);
            user = userMapper.selectById(1l);
            Assert.assertEquals("admin", user.getUserName());
            Assert.assertEquals("ivy.test@mybaitis.com", user.getUserEmail());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user1 = userMapper.selectById(1l);
            Assert.assertNotNull(user1);
            Assert.assertEquals(1, userMapper.deleteById(1l));
            Assert.assertNull(userMapper.selectById(1l));
            SysUser user2 = userMapper.selectById(1001l);
            Assert.assertNotNull(user2);
            Assert.assertEquals(1, userMapper.deleteById(user2));
            Assert.assertNull(userMapper.selectById(1001l));
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByUser() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("ad");
            List<SysUser> userList = userMapper.selectByUser(user);
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() > 0);

            user = new SysUser();
            user.setUserEmail("test@mybatis.tk");
            List<SysUser> userList1 = userMapper.selectByUser(user);
            Assert.assertNotNull(userList1);
            Assert.assertTrue(userList1.size() > 0);

            user = new SysUser();
            user.setUserName("ad");
            user.setUserEmail("test@mybatis.tk");
            List<SysUser> userList2 = userMapper.selectByUser(user);
            Assert.assertTrue(userList2.size() == 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdOrName() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("admin");
            user.setId(1l);
            SysUser result = userMapper.selectByIdOrName(user);
            Assert.assertNotNull(result);

            user.setId(null);
            SysUser result2 = userMapper.selectByIdOrName(user);
            Assert.assertNotNull(result2);

            user.setUserName(null);
            SysUser result3 = userMapper.selectByIdOrName(user);
            Assert.assertNull(result3);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByList() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> idList = new ArrayList<>();
            idList.add(1l);
            idList.add(1001l);
            List<SysUser> userList = userMapper.selectByIdList(idList);
            Assert.assertEquals(2, userList.size());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertList() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                SysUser user = new SysUser();
                user.setUserName("test" + i);
                user.setUserPassword("654321" + i);
                user.setUserEmail("i.xue@sap.com");
                user.setUserInfo("test info");
                user.setHeadImg(new byte[]{1,2,3});
                user.setCreateTime(new Date());
                userList.add(user);
            }

            int result = userMapper.insertList(userList);
            Assert.assertEquals(2, result);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByMap() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1l);
            map.put("user_name", "testTest");
            map.put("user_password", "12345678");
            int result = userMapper.updateByMap(map);
            Assert.assertEquals(1, result);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleById(1001l);
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getRole());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById2() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleById2(1001l);
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getRole());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdSelect() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleByIdSelect(1001l);
            Assert.assertNotNull(user);
            System.out.println("call user.getRole()");
            Assert.assertNotNull(user.getRole());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRoles() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = userMapper.selectAllUserAndRoles();
            System.out.println("userList size:" + userList.size());
            for (SysUser user: userList) {
                System.out.println("user name :" + user.getUserName());
                for (SysRole role : user.getRoleList()) {
                    System.out.println("role name :" +role.getRoleName());
                    for (SysPrivilege privilege : role.getPrivilegeList()) {
                        System.out.println("privilege name:" + privilege.getPrivilegeName());
                    }
                }
            }
        } finally {
            sqlSession.close();
        }
    }

}
