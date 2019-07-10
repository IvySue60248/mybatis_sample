package ivy.mybatis.simple.mapper;

import ivy.mybatis.simple.model.Country;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CountryMapperTest extends BaseMapperTest{


    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            List<Country> countryList = countryMapper.selectAll();
            printCountryList(countryList);
        } finally {
            sqlSession.close();
        }
    }

    private void printCountryList(List<Country> countryList) {
        for (Country country : countryList) {
            System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }
}
