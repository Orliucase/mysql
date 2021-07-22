package cn.jas.mapper;

import java.util.List;

public interface FileDbMapper {

    List<String> queryFiledsByTableName(String tableName);

    String queryNullAbleByTableNameAndColum(String tableName,String column);
}
