package com.sg.simplyrugby.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.mapper.SysPermissionMapper;
import com.sg.simplyrugby.mapper.SysPermissionRoleMapper;
import com.sg.simplyrugby.model.SysMenu;
import com.sg.simplyrugby.model.SysPermission;
import com.sg.simplyrugby.model.SysPermissionRole;
import com.sg.simplyrugby.util.ConvertUtil;
import com.sg.simplyrugby.util.SnowflakeIdWorker;
import com.sg.simplyrugby.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysPermissionService extends ServiceImpl<SysPermissionMapper, SysPermission> {

    //权限mapper
    @Autowired
    private SysPermissionMapper sysPermissionMapper;


    //权限角色关联表
    @Autowired
    private SysPermissionRoleMapper permissionRoleMapper;

    /**
     * 分页查询
     *
     * @return
     */
    public PageInfo<SysPermission> list(PageDTO pageDTO, String searchText) {
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getLimit());
        List<SysPermission> list = sysPermissionMapper.selectList(new LambdaQueryWrapper<SysPermission>().like(SysPermission::getName, searchText));
        PageInfo<SysPermission> pageInfo = new PageInfo<SysPermission>(list);
        return pageInfo;
    }


    public int deleteByPrimaryKey(String ids) {
        //转成集合
        List<String> lista = ConvertUtil.toListStrArray(ids);


        //判断角色是否删除去除

        List<SysPermissionRole> sysPermissionRoles = permissionRoleMapper.selectList(new LambdaQueryWrapper<SysPermissionRole>().in(SysPermissionRole::getPermissionId, lista));
        if (sysPermissionRoles.size() > 0) {//有角色外键
            return -2;
        }

        //判断是否有子集
        List<SysPermission> sysPermissions = sysPermissionMapper.selectList(new LambdaQueryWrapper<SysPermission>().in(SysPermission::getId, lista));
        boolean lag = false;
		for (SysPermission sysPermission : sysPermissions) {
			if(sysPermission.getChildCount()>0) {
				lag=true;
			}
		}
        if (lag) {//有子集 无法删除
            return -1;
        } else {//删除操作
            int i = sysPermissionMapper.delete(new LambdaQueryWrapper<SysPermission>().in(SysPermission::getId, lista));
            if (i > 0) {//删除成功
                return 1;
            } else {//删除失败
                return 0;
            }

        }
    }

    /**
     * 根据用户id获取用户角色如果用户为null 获取所有权限
     *
     * @return
     */
    public List<SysPermission> getall(String userid) {
        if (StringUtils.isEmpty(userid)) {
            return sysPermissionMapper.selectList(
                    new LambdaQueryWrapper<SysPermission>()
                            .isNotNull(SysPermission::getOrderNum)
                            .orderByAsc(SysPermission::getOrderNum)
            );
        }
        return sysPermissionMapper.getByAdminUserId(userid);
    }

    /**
     * 根据用户id查询菜单栏
     *
     * @return
     */
    public List<SysMenu> getSysMenus(String userid) {
        List<SysMenu> treeList;
        List<SysPermission> menuList = getall(userid);
        treeList = getSysMenus(menuList, "0");
        return treeList;
    }

    /**
     * 递归查询权限
     *
     * @param treeList
     * @param parentId
     * @return
     */
    private List<SysMenu> getSysMenus(List<SysPermission> treeList, String parentId) {
        List<SysMenu> SysMenuList = new ArrayList<SysMenu>();
        if (StringUtils.isNotNull(parentId) && treeList != null && treeList.size() > 0) {
            List<SysMenu> childList = null;
            for (SysPermission tsysPermission : treeList) {
                if (tsysPermission.getPid().equals(parentId)) {
                    if (tsysPermission.getChildCount() != null && tsysPermission.getChildCount() > 0) {
                        childList = getSysMenus(treeList, tsysPermission.getId());
                    }
                    SysMenu sysMenu = new SysMenu(tsysPermission.getId(), tsysPermission.getPid(), tsysPermission.getName(), tsysPermission.getType(), tsysPermission.getIsBlank(), tsysPermission.getIcon(), tsysPermission.getUrl(), childList);
                    SysMenuList.add(sysMenu);
                    childList = null;
                }
            }
        }


        return SysMenuList;
    }


    public int insertSelective(SysPermission record) {
        //添加雪花主键id
        record.setId(SnowflakeIdWorker.getUUID());
        //判断为目录的时候添加父id为0
		/*if(record!=null&&record.getType()==0){
			record.setPid("1");
		}*/
        //默认设置不跳转
        if (record.getIsBlank() == null) {
            record.setIsBlank(0);
        }
        return sysPermissionMapper.insert(record);
    }

    public SysPermission selectByPrimaryKey(String id) {

        return sysPermissionMapper.selectById(id);
    }


    public int updateByPrimaryKeySelective(SysPermission record) {
        //默认设置不跳转
        if (record.getIsBlank() == null) {
            record.setIsBlank(0);
        }
        return sysPermissionMapper.updateById(record);
    }

    public int updateByPrimaryKey(SysPermission record) {
        //默认设置不跳转
        if (record.getIsBlank() == null) {
            record.setIsBlank(0);
        }
        return sysPermissionMapper.updateById(record);
    }

    /**
     * 修改权限状态展示或者不展示
     *
     * @param record
     * @return
     */
    public int updateVisible(SysPermission record) {
        return sysPermissionMapper.updateById(record);
    }


    /**
     * 检查权限名字
     *
     * @return
     */
    public int checkNameUnique(SysPermission sysPermission) {
        List<SysPermission> list = sysPermissionMapper.selectList(new LambdaQueryWrapper<SysPermission>().in(SysPermission::getName, sysPermission.getName()));
        return list.size();
    }

    /**
     * 检查权限URL
     *
     * @return
     */
    public int checkURLUnique(SysPermission sysPermission) {

        List<SysPermission> list = sysPermissionMapper.selectList(new LambdaQueryWrapper<SysPermission>().in(SysPermission::getUrl, sysPermission.getUrl()));
        return list.size();
    }

    /**
     * 检查权限perms字段
     *
     * @return
     */
    public int checkPermsUnique(SysPermission sysPermission) {

        List<SysPermission> list = sysPermissionMapper.selectList(new LambdaQueryWrapper<SysPermission>().in(SysPermission::getPerms, sysPermission.getPerms()));
        return list.size();
    }


}
