package cn.jas;

import cn.jas.mapper.FileDbMapper;
import cn.jas.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class LoadData {

    @Autowired
    private FileDbMapper fileDbMapper;
    private String columnSplit = "";
    private String rowSplit = "";

    public String fileStreamToDb(String filePath,String tableName){
        String fields = "";
        // 查询tableName下所有表字段
        List<String> fieldList = fileDbMapper.queryFiledsByTableName(tableName);
        StringBuilder stringBuilder = new StringBuilder();

        StringBuilder setString = new StringBuilder();
        int count=0;
        String date = DateUtils.formatDate(new Date());
        for(int i=1;i<fieldList.size();i++){
            String isNullAble = fileDbMapper.queryNullAbleByTableNameAndColum(tableName,fieldList.get(i));
            String data_type = "";
            if("NO".equals(isNullAble)){
                stringBuilder.append("@`" + fieldList.get(i) + "`,");
                setString.append("`" + fieldList.get(i) + "`=if(@`" + fieldList.get(i) + "`='','',@`" + fieldList.get(i)+"`),");
                count++;
            }else {
                stringBuilder.append("`" + fieldList.get(i) + "`,");
            }

        }
        String sbStr = stringBuilder.toString();
        sbStr = sbStr.substring(0,sbStr.length() -1);
        fields = "("+ sbStr+")";
        String setStr = "";
        if(count>0){
            setStr = setString.toString();
            sbStr = setStr.substring(0,sbStr.length() -1);
            sbStr = "set" + sbStr;
        }
        String loadDataSql = "load data local infile '' ignore into table "+ tableName + "character set utf8mb4 fidlds "+
        " terminated by X'" + columnSplit + "' lines terminated by X'" + rowSplit +" '" + fields + setStr;
        //执行sql
        return null;
    }
}
