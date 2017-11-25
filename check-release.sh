#!/bin/bash
string=$(cat CURRENT_CHANGELOG.MD)
export CAN_RELEASE="false"
if [[ $string == *"#release"* ]]; then
    export CAN_RELEASE="true"
fi
