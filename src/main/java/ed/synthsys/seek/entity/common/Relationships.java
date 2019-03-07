/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.synthsys.seek.entity.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Johnny Hay
 */
public interface Relationships {   
    @JsonProperty("creators")
    public Creators getCreators();

    @JsonProperty("creators")
    public void setCreators(Creators creators);
}
