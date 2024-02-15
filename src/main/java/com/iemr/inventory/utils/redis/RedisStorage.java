/*
* AMRIT – Accessible Medical Records via Integrated Technology 
* Integrated EHR (Electronic Health Records) Solution 
*
* Copyright (C) "Piramal Swasthya Management and Research Institute" 
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.inventory.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class RedisStorage {
	@Autowired
	private LettuceConnectionFactory connection;

	Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	private @Value("${iemr.session.expiry.time}") int sessionExpiryTimeInSec;

	public String getSessionObject(String key) throws Exception {

		RedisConnection redisCon = connection.getConnection();
		byte[] sessionData = redisCon.stringCommands().get(key.getBytes());
		String userRespFromRedis = null;
		if (sessionData != null) {
			userRespFromRedis = new String(sessionData);
		}
		if ((userRespFromRedis != null) && (userRespFromRedis.trim().length() != 0)) {

		} else {
			throw new Exception(
					"Unable to fetch session object from Redis server,either session key is invalid or expired.");
		}

		return userRespFromRedis;
	}

	public String updateSessionObject(String key) throws Exception {
		RedisConnection redisCon = connection.getConnection();

		byte[] sessionData = redisCon.stringCommands().get(key.getBytes());

		if ((sessionData != null) && sessionData.length > 0) {
			logger.info("updating session time in redis for " + key);
			redisCon.stringCommands().set(key.getBytes(), sessionData, Expiration.seconds(sessionExpiryTimeInSec),
					SetOption.UPSERT);
		} else {
			throw new Exception("Unable to fetch session object from Redis server");
		}

		return key;
	}

	public void updateConcurrentSessionObject(String value) {
		try {
			JsonObject jsnOBJ = new JsonObject();

			JsonParser jsnParser = new JsonParser();
			JsonElement jsnElmnt = jsnParser.parse(value);
			jsnOBJ = jsnElmnt.getAsJsonObject();
			logger.info("updating con-current key:" + jsnOBJ.get("userName"));
			if (jsnOBJ.has("userName") && jsnOBJ.get("userName") != null) {
				updateSessionObject(jsnOBJ.get("userName").getAsString().trim().toLowerCase());
			}
		} catch (Exception e) {

		}
	}
}
