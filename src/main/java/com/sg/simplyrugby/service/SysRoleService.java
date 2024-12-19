package com.sg.simplyrugby.service;

import cn.hutool.core.util.RandomUtil;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.mapper.SysPermissionRoleMapper;
import com.sg.simplyrugby.mapper.SysRoleMapper;
import com.sg.simplyrugby.model.SysPermissionRole;
import com.sg.simplyrugby.model.SysRole;
import com.sg.simplyrugby.util.ConvertUtil;
import com.sg.simplyrugby.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> {
    //角色mapper
    @Autowired
    private SysRoleMapper tsysRoleMapper;

    //自动生成的权限角色映射mapper
    @Autowired
    private SysPermissionRoleMapper tsysPermissionRoleMapper;


    /**
     * 分页查询
     *
     * @return
     */
    public PageInfo<SysRole> list(PageDTO pageDTO) {

        PageHelper.startPage(pageDTO.getPage(), pageDTO.getLimit());
        List<SysRole> list = tsysRoleMapper.selectList(new LambdaQueryWrapper<>());
        PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list);
        return pageInfo;
    }

    /**
     * 查询全部角色集合
     *
     * @return
     */
    public List<SysRole> queryList() {
        return tsysRoleMapper.selectList(new LambdaQueryWrapper<>());
    }


    /**
     *
     */
    @Transactional
    public int deleteByPrimaryKey(String ids) {
        List<String> lista = ConvertUtil.toListStrArray(ids);
        //先删除角色下面的所有权限
        tsysPermissionRoleMapper.delete(new LambdaQueryWrapper<SysPermissionRole>().in(SysPermissionRole::getRoleId, lista));
        return tsysRoleMapper.delete(new LambdaQueryWrapper<SysRole>().in(SysRole::getId, lista));
    }


    public int insertSelective(SysRole record) {
        //添加雪花主键id
        record.setId(SnowflakeIdWorker.getUUID());
        return tsysRoleMapper.insert(record);
    }

    /**
     * 添加角色绑定权限
     *
     * @param record 角色信息
     * @param prem   权限id集合
     * @return
     */
    @Transactional
    public int insertRoleAndPrem(SysRole record, String prem) {
        //添加雪花主键id
        String roleid = SnowflakeIdWorker.getUUID();
        record.setId(roleid);
        //添加权限
        List<String> prems = ConvertUtil.toListStrArray(prem);
        for (String premid : prems) {
            SysPermissionRole tsysPermissionRole = new SysPermissionRole(RandomUtil.randomString(16), roleid, premid);
            tsysPermissionRoleMapper.insert(tsysPermissionRole);
        }
        return tsysRoleMapper.insert(record);
    }

    public SysRole selectByPrimaryKey(String id) {

        return tsysRoleMapper.selectById(id);
    }


    public int updateByPrimaryKeySelective(SysRole record) {
        return tsysRoleMapper.updateById(record);
    }

    /**
     * 修改用户角色 以及下面绑定的权限
     *
     * @return
     */
    @Transactional
    public int updateRoleAndPrem(String roleId, String powerIds) {
        //先删除角色下面的所有权限

        tsysPermissionRoleMapper.delete(new LambdaQueryWrapper<SysPermissionRole>().eq(SysPermissionRole::getRoleId, roleId));
        //添加权限关联
        List<String> prems = ConvertUtil.toListStrArray(powerIds);
        int i = 0;
        for (String pre : prems) {
            SysPermissionRole permissionRole = new SysPermissionRole(RandomUtil.randomString(16), roleId, pre);
            tsysPermissionRoleMapper.insert(permissionRole);
            i++;
        }

        return i;
    }


    public int deleteByExample(SysRole example) {
        return tsysRoleMapper.deleteById(example);
    }

    /**
     * 检查角色name
     *
     * @return
     */
    public int checkNameUnique(SysRole tsysRole) {

        List<SysRole> list = tsysRoleMapper.selectList(new LambdaQueryWrapper<SysRole>().eq(SysRole::getName, tsysRole.getName()));
        return list.size();
    }


}
