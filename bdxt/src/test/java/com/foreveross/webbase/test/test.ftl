<#if score gte 60>
 及格
<#elseif score gte 80&&score lte 90>
良好
<#else>
高材生
</#if>
${time?time}
--------------空值判断、默认值---------------
${name!"未定义"}
--------------判断值是否存在------------
<#if name??>
  name存在
<#else>
 name不存在
</#if>
<#list "12,13,14,15"?split(",") as item>
${item}
</#list>