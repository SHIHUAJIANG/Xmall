package top.flander.xmall.member.mapper;


import tk.mybatis.mapper.common.Mapper;
import top.flander.xmall.bean.UmsMember;

import java.util.List;

public interface UserMapper extends Mapper<UmsMember>{

    List<UmsMember> selectAllUser();

}
