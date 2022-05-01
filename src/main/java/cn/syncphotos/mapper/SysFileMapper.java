package cn.syncphotos.mapper;

import cn.syncphotos.entity.SysFile;
import cn.syncphotos.vo.FileInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysFileMapper extends BaseMapper<SysFile> {

    IPage<FileInfoVO> findPage(Page page);
}
