/**
 * Copyright (c) 2020 by rolhai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.rolhai.cms.api.model;

public class Meterpoint {

    private String type;
    private String subtype;
    private String date;
    private Double value;
    private String unit;

    @Override
    public String toString() {
        return "Meterpoint [type=" + type + ", subtype=" + subtype + ", date=" + date + ", value=" + value + ", unit="
                + unit + "]";
    }

    public Meterpoint type(String type) {
        this.type = type;
        return this;
    }

    public Meterpoint subtype(String subtype) {
        this.subtype = subtype;
        return this;
    }

    public Meterpoint date(String date) {
        this.date = date;
        return this;
    }

    public Meterpoint value(Double value) {
        this.value = value;
        return this;
    }

    public Meterpoint unit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
