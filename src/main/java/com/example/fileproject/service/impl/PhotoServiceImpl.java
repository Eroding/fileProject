package com.example.fileproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fileproject.constant.QiniuyunConstant;
import com.example.fileproject.domain.Photo;
import com.example.fileproject.domain.PhotoLabel;
import com.example.fileproject.dto.PhotoDTO;
import com.example.fileproject.service.PhotoLabelService;
import com.example.fileproject.service.PhotoService;
import com.example.fileproject.mapper.PhotoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author edison
 * @description 针对表【photo(照片)】的数据库操作Service实现
 * @createDate 2024-12-14 16:41:20
 */
@Service
@Slf4j
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo>
        implements PhotoService {


    @Resource
    private PhotoMapper photoMapper;

    @Resource
    private PhotoLabelService photoLabelService;


    @Override
    public String putFile(MultipartFile image) throws IOException {

        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构建新的文件名
        //文件扩展名
        String extname = originalFilename.substring(originalFilename.lastIndexOf("."));
        //随机名+文件扩展名
        String newFileName = UUID.randomUUID().toString() + extname;

        //将文件存储在服务器的磁盘目录
        String s = "/users/edison/code/" + newFileName;
        image.transferTo(new File(s));
        return s;

    }

    @Override
    public PageInfo<Photo> pageByCondition(PhotoDTO photo) {
        Page<Photo> pageQuery = new Page<>(photo.getPage(), photo.getRows());
        pageQuery.setOptimizeCountSql(false);
        PageHelper.startPage(photo.getPage(), photo.getRows());
        log.info("photo当前page:{},当前rows:{}", photo.getPage(), photo.getRows());
        List<Photo> photos = photoMapper.selectPhotoPage(pageQuery, photo);
        return new PageInfo<>(photos);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removePhotoById(Integer id) {
        Photo photo = this.getById(id);
        if (photo == null) {
            return true;
        }
        String key = photo.getUrl();
        boolean b = this.removeById(id);
        if (!b) {
            return false;
        }

        //设置需要操作的账号的AK和SK
        Auth auth = Auth.create(QiniuyunConstant.ACCESS_KEY, QiniuyunConstant.SECRET_KEY);

        Zone z = Zone.zone0();
        Configuration c = new Configuration(z);

        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth, c);
        //要测试的空间和key，并且这个key在你空间中存在

        try {
            //调用delete方法移动文件
            bucketManager.delete(QiniuyunConstant.bucket, key);
        } catch (QiniuException e) {
            e.printStackTrace();
        }

        //删除绑定关系

        LambdaQueryWrapper<PhotoLabel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PhotoLabel::getPhotoId, id);
        photoLabelService.remove(wrapper);
        return true;
    }

    @Override
    public boolean removePhotoByIds(List<Integer> ids) {
        List<Photo> photos = this.listByIds(ids);
        if (photos == null) {
            return true;
        }
        List<String> urlIds = photos.stream().map(e -> e.getUrl()).collect(Collectors.toList());
        boolean b = this.removeByIds(ids);
        if (!b) {
            return false;
        }

        //设置需要操作的账号的AK和SK
        Auth auth = Auth.create(QiniuyunConstant.ACCESS_KEY, QiniuyunConstant.SECRET_KEY);

        Zone z = Zone.zone0();
        Configuration c = new Configuration(z);

        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth, c);
        //要测试的空间和key，并且这个key在你空间中存在

        try {
            //调用delete方法移动文件
            for (String key : urlIds) {
                bucketManager.delete(QiniuyunConstant.bucket, key);
            }
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        LambdaQueryWrapper<PhotoLabel> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(PhotoLabel::getPhotoId, ids);
        photoLabelService.remove(wrapper);
        return true;
    }
}




