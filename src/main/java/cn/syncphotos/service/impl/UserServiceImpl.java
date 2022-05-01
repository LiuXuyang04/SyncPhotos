package cn.syncphotos.service.impl;

import cn.syncphotos.entity.User;
import cn.syncphotos.mapper.UserMapper;
import cn.syncphotos.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
