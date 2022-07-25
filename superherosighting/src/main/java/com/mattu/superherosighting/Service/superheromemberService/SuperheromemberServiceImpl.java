package com.mattu.superherosighting.Service.superheromemberService;

import com.mattu.superherosighting.Dao.OrgnizationDao.OrgnizationDao;
import com.mattu.superherosighting.Dao.SuperheroDao.SuperheroDao;
import com.mattu.superherosighting.Dao.SuperheromemberDao.SuperheromemberDao;
import com.mattu.superherosighting.Model.Orgnization;
import com.mattu.superherosighting.Model.Superhero;
import com.mattu.superherosighting.Model.Superheromember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuperheromemberServiceImpl implements SuperheromemberService{

    @Autowired
    SuperheromemberDao smDao;

    @Autowired
    SuperheroDao sDao;

    @Autowired
    OrgnizationDao oDao;

    @Override
    public Superheromember getSuperheromemberBySid(int sid) {
        return smDao.getSuperheromemberBySid(sid);
    }

    @Override
    public Superheromember updateMemberBySid(int sid, int oid) {
        Superheromember sm = smDao.getSuperheromemberBySid(sid);
        sm.setOrg(oDao.getOrgnizationById(oid));
        return smDao.updateSuperheromember(sm);
    }

    @Override
    public List<Superhero> getSuperheromemberByOid(int oid) {
        List<Superheromember> list = smDao.getAllSuperheroByOid(oid);
        List<Superhero> res = new ArrayList<>();
        for(Superheromember sm : list){
            res.add(sm.getHero());
        }
        return res;
    }

    @Override
    public String getHeroStringByOid(int oid) {
        StringBuffer sb = new StringBuffer();
        List<Superheromember> list = smDao.getAllSuperheroByOid(oid);

        if (list.size() == 0) {
            return "No Superhero yet!";
        }
        for(Superheromember sm:list){
            sb.append(sm.getHero().getSname());
            sb.append(",");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    @Override
    public int updateSuperheromember(int oid,List<Integer> sid) {
        List<Superheromember> allSuperheroByOid = smDao.getAllSuperheroByOid(oid);
        if(sid.size()==0){
            for(Superheromember sm : allSuperheroByOid) {
                smDao.deleteSuperheromemberById(sm.getSMid());
            }
        }else{
            for(Superheromember sm : allSuperheroByOid){
                if(!sid.contains(sm.getHero().getSid())){
                    smDao.deleteSuperheromemberById(sm.getSMid());
                }else{

                    sid.remove(sid.indexOf(sm.getHero().getSid()));
                }
            }

            if(sid.size()!=0){
                for(Integer id:sid){
                    Superheromember newSM = new Superheromember();
                    Superhero newS = new Superhero();
                    newS.setSid(id);
                    Orgnization newO = new Orgnization();
                    newO.setOid(oid);
                    newSM.setHero(newS);
                    newSM.setOrg(newO);
                    smDao.deleteSuperheromemberById(smDao.getSuperheromemberBySid(id).getSMid());
                    smDao.addSuperheromember(newSM);
                }
            }
        }

        return oid;
    }
}
