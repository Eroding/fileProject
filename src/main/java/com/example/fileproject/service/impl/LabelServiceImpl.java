package com.example.fileproject.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fileproject.common.ResponseResult;
import com.example.fileproject.domain.Label;
import com.example.fileproject.dto.LabelVO;
import com.example.fileproject.service.LabelService;
import com.example.fileproject.mapper.LabelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author edison
* @description 针对表【label(标签)】的数据库操作Service实现
* @createDate 2024-12-14 16:40:56
*/
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label>
    implements LabelService {

    @Override
    public List<LabelVO> listForTree() {
        LambdaQueryWrapper<Label> wrapper = Wrappers.<Label>lambdaQuery()
                .eq( Label::getIsDelete,0);
        List<Label> list = this.list(wrapper);
        List<LabelVO> labelVOList = BeanUtil.copyToList(list, LabelVO.class);
        //获取父节点
        List<LabelVO> collect = labelVOList.stream().filter(t -> t.getParentId() == 0).map(
                m -> {
                    m.setChildrenLabel(getChildren(m, labelVOList));
                    return m;
                }
        ).collect(Collectors.toList());
        return collect;
    }



    /**
     * 递归查询子节点
     * @param root  根节点
     * @param all   所有节点
     * @return 根节点信息
     */
    public static List<LabelVO> getChildren(LabelVO root, List<LabelVO> all) {
        List<LabelVO> children = all.stream().filter(t -> {
            return Objects.equals(t.getParentId(), root.getId());
        }).map(
                m -> {
                    m.setChildrenLabel(getChildren(m, all));
                    return m;
                }
        ).collect(Collectors.toList());
        return children;
    }


}




