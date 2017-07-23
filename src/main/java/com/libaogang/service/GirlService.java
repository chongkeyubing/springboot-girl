package com.libaogang.service;

import com.libaogang.domain.Girl;
import com.libaogang.enums.ResultEnum;
import com.libaogang.exception.GirlException;
import com.libaogang.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional         //事务,同时插入两个女孩
    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(18);
        girlRepository.save(girlA);


        Girl girlB = new Girl();
        girlB.setCupSize("BBBB");    //数据库该字段长度为1，这条数据无法插入
        girlB.setAge(19);
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) throws Exception{
        Girl girl = girlRepository.findOne(id);
        if (girl == null){
            throw new GirlException(ResultEnum.NOT_EXSIT);
        }
        Integer age = girl.getAge();
        if (age < 10) {
            //返回"你还在上小学吧" code=100
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if (age > 10 && age < 16) {
            //返回"你可能在上初中" code=101
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }

        //如果>16岁,加钱
        //...
    }

    /**
     * 通过Id查询一个女生的信息
     * @param id
     * @return
     */
    public Girl findOne(Integer id) {
        return girlRepository.findOne(id);
    }
}
