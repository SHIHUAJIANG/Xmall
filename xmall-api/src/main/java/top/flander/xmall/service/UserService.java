package top.flander.xmall.service;

import top.flander.xmall.bean.UmsMember;
import top.flander.xmall.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {

    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);
}
