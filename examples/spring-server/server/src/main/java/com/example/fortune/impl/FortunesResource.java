/*
   Copyright (c) 2012 LinkedIn Corp.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.example.fortune.impl;

import com.linkedin.restli.server.annotations.RestLiCollection;
import com.linkedin.restli.server.resources.CollectionResourceTemplate;
import com.example.fortune.Fortune;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Very simple RestLi Resource that serves up a fortune cookie.
 *
 * @author Doug Young
 */
@RestLiCollection(name = "fortunes", namespace = "com.example.fortune")
public class FortunesResource extends CollectionResourceTemplate<Long, Fortune>
{
  /**
   * Rest.li uses JSR-330 annotation to inject beans.  When rest.li's spring bridge, all spring beans are available.
   */
  @Inject @Named("fortunesBean")
  public FortunesBean _fortunesBean;

  @Override
  public Fortune get(Long key)
  {
    // Retrieve the requested fortune
    String fortune = _fortunesBean.getFortune(key);
    if(fortune == null)
      fortune = "Your luck has run out. No fortune for id="+key;

    // return an object that represents the fortune cookie
    return new Fortune().setFortune(fortune);
  }
}
