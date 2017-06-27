#!/usr/bin/env bash

PROJECT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && cd ../../.. && pwd )"
while read p; do
  artifact_id=${p%:*}
  echo "artifact_id=${artifact_id}"
  version=${p#*:}
  echo "version=${version}"
  pom_url1="https://raw.githubusercontent.com/jenkinsci/${artifact_id}-plugin/v${version}/pom.xml"
  pom_url2="https://raw.githubusercontent.com/jenkinsci/${artifact_id}/v${version}/pom.xml"
  pom_url3="https://raw.githubusercontent.com/jenkinsci/${artifact_id}/${artifact_id}-${version}/pom.xml"
  pom_url4="https://raw.githubusercontent.com/jenkinsci/${artifact_id}-plugin/${artifact_id}-${version}/pom.xml"
  group_id=$( curl --silent ${pom_url1} --next ${pom_url2} --next ${pom_url3} --next ${pom_url4} | grep -m 1 'groupId' | sed 's#[ ]*<\/*groupId>[ ]*##g' )
  echo "group_id=${group_id}"
done < ${PROJECT_DIR}/src/main/resources/plugins.txt