package ivy.mybatis.simple.mapper;

import ivy.mybatis.simple.model.SysPrivilege;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PrivilegeMapperTest extends BaseMapperTest{

    @Test
    public void testSelectPrivilegeByRoleId() {
        SqlSession sqlSession = getSqlSession();
        try {
            PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
            List<SysPrivilege> privilegeList = privilegeMapper.selectPrivilegeByRoleId(1l);
            Assert.assertNotNull(privilegeList);
            Assert.assertEquals(3, privilegeList.size());

        } finally {
            sqlSession.close();
        }
    }

}
