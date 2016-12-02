DELETE FROM TICKET_STATUS;
INSERT INTO TICKET_STATUS VALUES ( 'Closed Auto Refreshed', 'CLOSED', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Closed IAL Fix', 'CLOSED', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Closed No Fix', 'CLOSED', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Closed No MFA', 'CLOSED', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Closed Not Replicable', 'CLOSED', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Closed Recent Fix', 'CLOSED', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Closed Valid Issue', 'CLOSED', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Closed Deployed', 'CLOSED', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Closing Comments', 'INPROGRESS', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Create Site Profile', 'INPROGRESS', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'On Hold', 'HOLD', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Ready To Be Deployed', 'RESOLVED', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'ReOpen', 'INPROGRESS', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Script Review', 'INPROGRESS', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Site Profile QA', 'INPROGRESS', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Ticket Created', 'CREATED', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Ticket Proxy Tech Support', 'INPROGRESS', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Ticket QA', 'INPROGRESS', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_STATUS VALUES ( 'Update Site Profile', 'INPROGRESS', 'admin', sysdate, 'admin', sysdate );

DELETE FROM TICKET_PANEL;
INSERT INTO TICKET_PANEL VALUES ( 'Accounts', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_PANEL VALUES ( 'Attachments', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_PANEL VALUES ( 'CaseDetails', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_PANEL VALUES ( 'Resolution', 'admin', sysdate, 'admin', sysdate );

DELETE FROM PANEL_SELECTION;
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( '2b00f6a2-134a-11e6-a148-3e1d05defe78', 'All', 'Reopen', 'All', 'admin', sysdate, 'admin', sysdate, 'Resolution' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( '2b00f77e-134a-11e6-a148-3e1d05defe78', 'All', 'Closed Deployed', 'All', 'admin', sysdate, 'admin', sysdate, 'Resolution' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( '12c30faa-be52-4bc1-b3ad-c2fbbce83b6c', 'All', 'Ready To Be Deployed', 'All', 'admin', sysdate, 'admin', sysdate, 'Resolution' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( '2b00f864-134a-11e6-a148-3e1d05defe78', 'SCRIPT_REVIEW', 'Script Review', 'All', 'admin', sysdate, 'admin', sysdate, 'Resolution' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( '2b00fdfa-134a-11e6-a148-3e1d05defe78', 'SOMQA', 'Ticket QA', 'All', 'admin', sysdate, 'admin', sysdate, 'Resolution' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( '2b010b88-134a-11e6-a148-3e1d05defe78', 'All', 'All', 'All', 'admin', sysdate, 'admin', sysdate, 'Attachments' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( '2b010f02-134a-11e6-a148-3e1d05defe78', 'All', 'All', 'REACTIVE', 'admin', sysdate, 'admin', sysdate, 'CaseDetails' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( '2b011163-134a-11e6-a148-3e1d05defe78', 'All', 'All', 'All', 'admin', sysdate, 'admin', sysdate, 'Accounts' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( 'b6d89e2c-230e-11e6-b67b-9e71128cae77', 'All', 'Closed Auto Refreshed', 'All', 'admin', sysdate, 'admin', sysdate, 'Resolution' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( 'b6d8a1f6-230e-11e6-b67b-9e71128cae77', 'All', 'Closed IAL Fix', 'All', 'admin', sysdate, 'admin', sysdate, 'Resolution' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( 'b6d8a3fe-230e-11e6-b67b-9e71128cae77', 'All', 'Closed No Fix', 'All', 'admin', sysdate, 'admin', sysdate, 'Resolution' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( 'b6d8a5c0-230e-11e6-b67b-9e71128cae77', 'All', 'Closed No MFA', 'All', 'admin', sysdate, 'admin', sysdate, 'Resolution' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( 'b6d8a76e-230e-11e6-b67b-9e71128cae77', 'All', 'Closed Not Replicable', 'All', 'admin', sysdate, 'admin', sysdate, 'Resolution' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( 'b6d8a91c-230e-11e6-b67b-9e71128cae77', 'All', 'Closed Recent Fix', 'All', 'admin', sysdate, 'admin', sysdate, 'Resolution' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( 'b6d8af5c-230e-11e6-b67b-9e71128cae77', 'All', 'Closed Valid Issue', 'All', 'admin', sysdate, 'admin', sysdate, 'Resolution' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( 'b49551c2-230f-11e6-b67b-9e71128cae77', 'All', 'Ready To Be Deployed', 'All', 'admin', sysdate, 'admin', sysdate, 'Resolution' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( '5ed35566-3171-11e6-ac61-9e71128cae77', 'SuperUser', 'All', 'All', 'admin', sysdate, 'admin', sysdate, 'Accounts' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( '5ed358f4-3171-11e6-ac61-9e71128cae77', 'SuperUser', 'All', 'All', 'admin', sysdate, 'admin', sysdate, 'Attachments' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( '5ed35ebc-3171-11e6-ac61-9e71128cae77', 'SuperUser', 'All', 'All', 'admin', sysdate, 'admin', sysdate, 'CaseDetails' );
INSERT INTO PANEL_SELECTION ( SELECTION_ID , USER_ROLE , TICKET_STATUS , TICKET_TYPE , CREATED_BY , CREATED_DT , MODIFIED_BY , MODIFIED_DT , PANEL_NAME ) VALUES ( '5ed360f6-3171-11e6-ac61-9e71128cae77', 'SuperUser', 'All', 'All', 'admin', sysdate, 'admin', sysdate, 'Resolution' );

DELETE FROM TICKET_ELEVATION;
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c1972-1347-11e6-a148-3e1d05defe78', 'Ticket Created', 'Scripter', 'Closed Auto Refreshed', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c1e68-1347-11e6-a148-3e1d05defe78', 'Ticket Created', 'Scripter', 'Update Site Profile', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c2070-1347-11e6-a148-3e1d05defe78', 'Ticket Created', 'Scripter', 'Script Review', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c21c4-1347-11e6-a148-3e1d05defe78', 'Ticket Proxy Tech Support', 'SuperUser', 'Ticket Created', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c2836-1347-11e6-a148-3e1d05defe78', 'On Hold', 'Scripter', 'Ticket Created', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c29c6-1347-11e6-a148-3e1d05defe78', 'Script Review', 'SCRIPT_REVIEW', 'ReOpen', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c2af2-1347-11e6-a148-3e1d05defe78', 'Script Review', 'SCRIPT_REVIEW', 'Ticket QA', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c2c0a-1347-11e6-a148-3e1d05defe78', 'Ticket QA', 'SOMQA', 'ReOpen', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c2d18-1347-11e6-a148-3e1d05defe78', 'Ticket QA', 'SOMQA', 'Closing Comments', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c2e26-1347-11e6-a148-3e1d05defe78', 'Ticket QA', 'SOMQA', 'Ready To Be Deployed', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c2126-1347-11e6-a148-3e1d05defe78', 'Ready To Be Deployed', 'SOMQA', 'Closed Deployed', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c2f2a-1347-11e6-a148-3e1d05defe78', 'Update Site Profile', 'SuperUser', 'Site Profile QA', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3330-1347-11e6-a148-3e1d05defe78', 'Ticket Created', 'Scripter', 'Closed IAL Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3448-1347-11e6-a148-3e1d05defe78', 'Create Site Profile', 'SuperUser', 'Site Profile QA', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c354c-1347-11e6-a148-3e1d05defe78', 'Site Profile QA', 'SuperUser', 'Ticket Created', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3754-1347-11e6-a148-3e1d05defe78', 'Ticket Created', 'Scripter', 'Closed No MFA', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3858-1347-11e6-a148-3e1d05defe78', 'Ticket Created', 'Scripter', 'Closed Recent Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3952-1347-11e6-a148-3e1d05defe78', 'Ticket Created', 'Scripter', 'Closed No Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3db2-1347-11e6-a148-3e1d05defe78', 'Ticket Created', 'Scripter', 'Closed Valid Issue', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3ed4-1347-11e6-a148-3e1d05defe78', 'Ticket Created', 'Scripter', 'Closed Not Replicable', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c401e-1347-11e6-a148-3e1d05defe78', 'Ticket Created', 'Scripter', 'On Hold', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c4136-1347-11e6-a148-3e1d05defe78', 'Ticket Created', 'Scripter', 'Ticket Proxy Tech Support', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680aad8-228f-11e6-b67b-9e71128cae77', 'ReOpen', 'Scripter', 'On Hold', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680ad62-228f-11e6-b67b-9e71128cae77', 'ReOpen', 'Scripter', 'Ticket Proxy Tech Support', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680ae84-228f-11e6-b67b-9e71128cae77', 'ReOpen', 'Scripter', 'Update Site Profile', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680af92-228f-11e6-b67b-9e71128cae77', 'ReOpen', 'Scripter', 'Script Review', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b078-228f-11e6-b67b-9e71128cae77', 'ReOpen', 'Scripter', 'Closed Auto Refreshed', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b3a2-228f-11e6-b67b-9e71128cae77', 'ReOpen', 'Scripter', 'Closed IAL Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b488-228f-11e6-b67b-9e71128cae77', 'ReOpen', 'Scripter', 'Closed No MFA', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b564-228f-11e6-b67b-9e71128cae77', 'ReOpen', 'Scripter', 'Closed Recent Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b640-228f-11e6-b67b-9e71128cae77', 'ReOpen', 'Scripter', 'Closed No Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b71c-228f-11e6-b67b-9e71128cae77', 'ReOpen', 'Scripter', 'Closed Valid Issue', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b7ee-228f-11e6-b67b-9e71128cae77', 'ReOpen', 'Scripter', 'Closed Not Replicable', 'admin', sysdate, 'admin', sysdate );

INSERT INTO TICKET_ELEVATION VALUES ( '0d0c1972-1347-11e6-a148-3e1d05defe79', 'Ticket Created', 'SuperScripter', 'Closed Auto Refreshed', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c1e68-1347-11e6-a148-3e1d05defe80', 'Ticket Created', 'SuperScripter', 'Update Site Profile', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c2070-1347-11e6-a148-3e1d05defe81', 'Ticket Created', 'SuperScripter', 'Script Review', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c2836-1347-11e6-a148-3e1d05defe82', 'On Hold', 'SuperScripter', 'Ticket Created', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3330-1347-11e6-a148-3e1d05defe83', 'Ticket Created', 'SuperScripter', 'Closed IAL Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3754-1347-11e6-a148-3e1d05defe84', 'Ticket Created', 'SuperScripter', 'Closed No MFA', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3858-1347-11e6-a148-3e1d05defe85', 'Ticket Created', 'SuperScripter', 'Closed Recent Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3952-1347-11e6-a148-3e1d05defe86', 'Ticket Created', 'SuperScripter', 'Closed No Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3db2-1347-11e6-a148-3e1d05defe87', 'Ticket Created', 'SuperScripter', 'Closed Valid Issue', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3ed4-1347-11e6-a148-3e1d05defe88', 'Ticket Created', 'SuperScripter', 'Closed Not Replicable', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c401e-1347-11e6-a148-3e1d05defe89', 'Ticket Created', 'SuperScripter', 'On Hold', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c4136-1347-11e6-a148-3e1d05defe90', 'Ticket Created', 'SuperScripter', 'Ticket Proxy Tech Support', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680aad8-228f-11e6-b67b-9e71128cae91', 'ReOpen', 'SuperScripter', 'On Hold', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680ad62-228f-11e6-b67b-9e71128cae92', 'ReOpen', 'SuperScripter', 'Ticket Proxy Tech Support', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680ae84-228f-11e6-b67b-9e71128cae93', 'ReOpen', 'SuperScripter', 'Update Site Profile', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680af92-228f-11e6-b67b-9e71128cae94', 'ReOpen', 'SuperScripter', 'Script Review', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b078-228f-11e6-b67b-9e71128cae95', 'ReOpen', 'SuperScripter', 'Closed Auto Refreshed', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b3a2-228f-11e6-b67b-9e71128cae96', 'ReOpen', 'SuperScripter', 'Closed IAL Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b488-228f-11e6-b67b-9e71128cae97', 'ReOpen', 'SuperScripter', 'Closed No MFA', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b564-228f-11e6-b67b-9e71128cae98', 'ReOpen', 'SuperScripter', 'Closed Recent Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b640-228f-11e6-b67b-9e71128cae99', 'ReOpen', 'SuperScripter', 'Closed No Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b71c-228f-11e6-b67b-9e71128ca100', 'ReOpen', 'SuperScripter', 'Closed Valid Issue', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b7ee-228f-11e6-b67b-9e71128ca101', 'ReOpen', 'SuperScripter', 'Closed Not Replicable', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c1972-1347-11e6-a148-3e1d05deff79', 'Ticket Created', 'SuperUser', 'Closed Auto Refreshed', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c1e68-1347-11e6-a148-3e1d05deff80', 'Ticket Created', 'SuperUser', 'Update Site Profile', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c2070-1347-11e6-a148-3e1d05deff81', 'Ticket Created', 'SuperUser', 'Script Review', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c2836-1347-11e6-a148-3e1d05deff82', 'On Hold', 'SuperUser', 'Ticket Created', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3330-1347-11e6-a148-3e1d05deff83', 'Ticket Created', 'SuperUser', 'Closed IAL Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3754-1347-11e6-a148-3e1d05deff84', 'Ticket Created', 'SuperUser', 'Closed No MFA', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3858-1347-11e6-a148-3e1d05deff85', 'Ticket Created', 'SuperUser', 'Closed Recent Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3952-1347-11e6-a148-3e1d05deff86', 'Ticket Created', 'SuperUser', 'Closed No Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3db2-1347-11e6-a148-3e1d05deff87', 'Ticket Created', 'SuperUser', 'Closed Valid Issue', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c3ed4-1347-11e6-a148-3e1d05deff88', 'Ticket Created', 'SuperUser', 'Closed Not Replicable', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c401e-1347-11e6-a148-3e1d05deff89', 'Ticket Created', 'SuperUser', 'On Hold', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( '0d0c4136-1347-11e6-a148-3e1d05deff90', 'Ticket Created', 'SuperUser', 'Ticket Proxy Tech Support', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680aad8-228f-11e6-b67b-9e71128caf91', 'ReOpen', 'SuperUser', 'On Hold', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680ad62-228f-11e6-b67b-9e71128caf92', 'ReOpen', 'SuperUser', 'Ticket Proxy Tech Support', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680ae84-228f-11e6-b67b-9e71128caf93', 'ReOpen', 'SuperUser', 'Update Site Profile', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680af92-228f-11e6-b67b-9e71128caf94', 'ReOpen', 'SuperUser', 'Script Review', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b078-228f-11e6-b67b-9e71128caf95', 'ReOpen', 'SuperUser', 'Closed Auto Refreshed', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b3a2-228f-11e6-b67b-9e71128caf96', 'ReOpen', 'SuperUser', 'Closed IAL Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b488-228f-11e6-b67b-9e71128caf97', 'ReOpen', 'SuperUser', 'Closed No MFA', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b564-228f-11e6-b67b-9e71128caf98', 'ReOpen', 'SuperUser', 'Closed Recent Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b640-228f-11e6-b67b-9e71128caf99', 'ReOpen', 'SuperUser', 'Closed No Fix', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b71c-228f-11e6-b67b-9e71128caf00', 'ReOpen', 'SuperUser', 'Closed Valid Issue', 'admin', sysdate, 'admin', sysdate );
INSERT INTO TICKET_ELEVATION VALUES ( 'c680b7ee-228f-11e6-b67b-9e71128caf01', 'ReOpen', 'SuperUser', 'Closed Not Replicable', 'admin', sysdate, 'admin', sysdate );




DELETE FROM ERROR_CODE;
INSERT INTO ERROR_CODE VALUES ( 160, 'SCRIPT ERROR', 'MISSING TRANSACTION', 'admin', sysdate, 'admin', sysdate );
INSERT INTO ERROR_CODE VALUES ( 100, 'GENERAL ERROR', 'MISSING TRANSACTION', 'admin', sysdate, 'admin', sysdate );
INSERT INTO ERROR_CODE VALUES ( 101, 'TEMP ERROR', 'MISSING TRANSACTION', 'admin', sysdate, 'admin', sysdate );
INSERT INTO ERROR_CODE VALUES ( 103, 'CREDENTIAL ERROR', 'MISSING TRANSACTION', 'admin', sysdate, 'admin', sysdate );
INSERT INTO ERROR_CODE VALUES ( 102, 'USER ACTION ERROR', 'USER ACTION ERROR', 'admin', sysdate, 'admin', sysdate );
INSERT INTO ERROR_CODE VALUES ( 0, 'NO ERROR', 'NO ERRROR', 'admin', sysdate, 'admin', sysdate );
INSERT INTO ERROR_CODE VALUES ( 108, 'TERMS CONDITION ERROR', 'MISSING TRANSACTION', 'admin', sysdate, 'admin', sysdate );
INSERT INTO ERROR_CODE VALUES ( 324, 'ACCOUNT MISMATCH ERROR', 'MISSING TRANSACTION', 'admin', sysdate, 'admin', sysdate );

DELETE FROM TICKET_PRIORITY;
INSERT into TICKET_PRIORITY values('P1', 10, 'admin', sysdate, 'admin', sysdate);
INSERT into TICKET_PRIORITY values('P2', 15, 'admin', sysdate, 'admin', sysdate);
INSERT into TICKET_PRIORITY values('P3', 20, 'admin', sysdate, 'admin', sysdate);

DELETE FROM TICKET_TYPE;
INSERT INTO TICKET_TYPE VALUES ('MANUAL','admin',sysdate,'admin',sysdate);
INSERT INTO TICKET_TYPE VALUES ('PROACTIVE','admin',sysdate,'admin',sysdate);
INSERT INTO TICKET_TYPE VALUES ('REACTIVE','admin',sysdate,'admin',sysdate);


INSERT INTO CLOSING_COMMENTS VALUES ('29c65814-1bdc-11e6-b6ba-3e1d05defe78','123','dummy.scr','0','General','This issue has been updated.',1,'admin',sysdate,'admin',sysdate);
INSERT INTO CLOSING_COMMENTS VALUES ('29c65b70-1bdc-11e6-b6ba-3e1d05defe78','123','dummy.scr','0','General','We are pleased to inform you that your issue has been resolved. The resolution has been implemented and this bank account should be ready for your use.',1,'admin',sysdate,'admin',sysdate);
INSERT INTO CLOSING_COMMENTS VALUES ('29c65f94-1bdc-11e6-b6ba-3e1d05defe78','5678','dummy.scr','0','General','This issue is resolved.',1,'admin',sysdate,'admin',sysdate);
INSERT INTO CLOSING_COMMENTS VALUES ('034c90b6-27af-11e6-b67b-9e71128cae77','123','dummy.scr','160','Transaction','This issue has been updated.',1,'admin',sysdate,'admin',sysdate);
INSERT INTO CLOSING_COMMENTS VALUES ('034c93b8-27af-11e6-b67b-9e71128cae77','123','dummy.scr','108','User Action','Please verify and Confirm on the website',1,'admin',sysdate,'admin',sysdate);
INSERT INTO CLOSING_COMMENTS VALUES ('034c97f0-27af-11e6-b67b-9e71128cae77','5678','dummy.scr','108','Terms and Condition','Please accept Terms and Conditions on the website.',1,'admin',sysdate,'admin',sysdate);

INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) 
values (146476264356641,'Test','P1','101','Layout Not found','PROACTIVE','Ticket Created','nmathur','1',sysdate,'dummy.scr',null,null,null, null, null,'dpalanisamy',sysdate,'dpalanisamy',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146476310043293,'Test','P1','101','Layout Not found','PROACTIVE','Ticket Created','testuser','1',sysdate,'dummy.scr',null,null,null, null, null,'dpalanisamy',sysdate,'dpalanisamy',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146476310043294,'Test','P1','101','Layout Not found','MANUAL','Ticket Created',null,'1',sysdate,'dummy.scr',null,null,null, null, null,'testuser',sysdate,'testuser',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146476310043295,'Test','P1','101','Layout Not found','MANUAL','Ticket Created',null,'1',sysdate,'dummy.scr',null,null,null, null, null,'kvenkateswaran',sysdate,'kvenkateswaran',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146476310043296,'Test','P1','101','Layout Not found','REACTIVE','Ticket Created',null,'1',sysdate,'dummy.scr',null,null,null, null, null,'kvenkateswaran',sysdate,'kvenkateswaran',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146476310043297,'Test','P1','101','Layout Not found','REACTIVE','Script Review',null,'1',sysdate,'dummy.scr',null,null,null, null, null,'kvenkateswaran',sysdate,'kvenkateswaran',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(1,'Test','P1','101','Layout Not found',null,null,null,'1',sysdate,'dummy.scr',null,null,null, null, null,'kvenkateswaran',sysdate,'kvenkateswaran',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146476310043298,'Test','P1','101','Layout Not found','REACTIVE','Closed Deployed',null,'1',sysdate,'dummy.scr',null,null,null, null, null,'kvenkateswaran',sysdate,'kvenkateswaran',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146476310043299,'Test','P1','101','Layout Not found','REACTIVE','Ticket QA',null,'1',sysdate,'dummy.scr',null,null,null, null, null,'kvenkateswaran',sysdate,'kvenkateswaran',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146476310043300,'Test','P1','101','Layout Not found',null,null,null,'1',sysdate,'dummy.scr',null,null,null, null, null,'kvenkateswaran',sysdate,'kvenkateswaran',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146476310043394,'Test','P1','101','Layout Not found','MANUAL','Closed Deployed','testuser','1',sysdate,'dummy.scr',null,null,null, null, null,'dpalanisamy',sysdate,'dpalanisamy',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146476310043494,'Test','P1','101','Layout Not found','MANUAL','Ticket Created','testuser','0',sysdate,'dummy.scr',null,null,null, null, null,'dpalanisamy',sysdate,'dpalanisamy',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146476310043495,'Test','P1','101','Layout Not found','MANUAL','Ticket Created','testuser','0',sysdate,'dummy.scr',null,null,null, null, null,'testuser',sysdate,'testuser',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146494401026689,'Test','P1','101','Layout Not found','MANUAL','Closed No Fix','testuser','0',sysdate,'dummy.scr',null,null,null, null, null,'testuser',sysdate,'testuser',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146494301026689,'Test','P1','101','Layout Not found','MANUAL','Ticket Created','testuser1','0',sysdate,'dummy.scr',null,null,null, null, null,'testuser',sysdate,'testuser',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146494301526689,'Test','P1','101','Layout Not found','MANUAL','Script Review','','0',sysdate,'dummy.scr',null,'','', '', null,'testuser',sysdate,'testuser',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values
(146476310043301,'Test','P1','101','Layout Not found','MANUAL','Closed No Fix','kvenkateswaran','1',sysdate,null,null,null,null, null, null,'kvenkateswaran',sysdate,'kvenkateswaran',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146476310043302,'Test','P1','102','Layout Not found','MANUAL','Ticket Created','kvenkateswaran','1',sysdate,'hdfc.scr',null,null,null, null, null,'kvenkateswaran',sysdate,'kvenkateswaran',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146476310043303,'Test','P1','0','Layout Not found','MANUAL','Ticket Created','kvenkateswaran','1',sysdate,'dummy.scr',null,null,null, null, null,'kvenkateswaran',sysdate,'kvenkateswaran',sysdate,null);
INSERT into TICKET (TICKET_ID,TICKET_DESCRIPTION,PRIORITY_LEVEL,ERROR_CODE,REFRESH_STATUS,TICKET_TYPE,STATUS_NAME,ASSIGNED_TO,UN_ASSIGNED,DUE_DATE,SCRIPT_NAME,PARENT_TICKET_ID,PEER_REVIEWER,REVIEW_ID,REVIEW_COMMENTS,CLOSING_COMMENTS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,CASE_ID) values 
(146476310043304,'Test','P1',null,'Layout Not found','MANUAL','Ticket Created','kvenkateswaran','1',sysdate,'dummy.scr',null,null,null, null, null,'kvenkateswaran',sysdate,'kvenkateswaran',sysdate,1);



INSERT into TICKET_TITLES (TITLE_IDENTIFIER,TICKET_ID,CUSTOMER_IDENTIFIER,CUSTOMER_ID_TYPE,PROVIDER_ID,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fb70550a9f8001550aa8417f0000',146476264356641,'bb0437f5-cedd-48fb-ad88-430fcefb1254','IAM_TYPE','72555616-45ea-4ea0-942b-26af17e12945','dpalanisamy',sysdate,'dpalanisamy',sysdate);
INSERT into TICKET_TITLES (TITLE_IDENTIFIER,TICKET_ID,CUSTOMER_IDENTIFIER,CUSTOMER_ID_TYPE,PROVIDER_ID,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fb70550aacdd01550aaf35d00000',146476310043293,'bb0437f5-cedd-48fb-ad88-430fcefb1254','IAM_TYPE','72555616-45ea-4ea0-942b-26af17e12945','dpalanisamy',sysdate,'dpalanisamy',sysdate);
INSERT into TICKET_TITLES (TITLE_IDENTIFIER,TICKET_ID,CUSTOMER_IDENTIFIER,CUSTOMER_ID_TYPE,PROVIDER_ID,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fb70550aacdd01550aaf35d00001',146476310043294,'bb0437f5-cedd-48fb-ad88-430fcefb1254','IAM_TYPE','72555616-45ea-4ea0-942b-26af17e12945','dpalanisamy',sysdate,'dpalanisamy',sysdate);


INSERT into TICKET_IMPACTED_ACCOUNTS (IMPACTED_ACCOUNT_ID,TITLE_IDENTIFIER,ACCOUNT_IDENTIFIER,CREDENTIAL_SET_ID,ERROR_CODE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fb70550a9f8001550aa845b20002','2c90fb70550a9f8001550aa8417f0000','85c455df-a257-475a-a8c7-1e3eefaae7a3','d3c06437-a306-4ac1-bc08-ebfd249bc2e1','101','dpalanisamy',sysdate,'dpalanisamy',sysdate);
INSERT into TICKET_IMPACTED_ACCOUNTS (IMPACTED_ACCOUNT_ID,TITLE_IDENTIFIER,ACCOUNT_IDENTIFIER,CREDENTIAL_SET_ID,ERROR_CODE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('2c90fb70550aacdd01550aaf39f30002','2c90fb70550aacdd01550aaf35d00000','85c455df-a257-475a-a8c7-1e3eefaae7a3','d3c06437-a306-4ac1-bc08-ebfd249bc2e1','101','dpalanisamy',sysdate,'dpalanisamy',sysdate);
INSERT into TICKET_IMPACTED_ACCOUNTS (IMPACTED_ACCOUNT_ID,TITLE_IDENTIFIER,ACCOUNT_IDENTIFIER,CREDENTIAL_SET_ID,ERROR_CODE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('2c90fb70550aacdd01550aaf39f30003','2c90fb70550aacdd01550aaf35d00001','85c455df-a257-475a-a8c7-1e3eefaae7a4','d3c06437-a306-4ac1-bc08-ebfd249bc2e2','101','dpalanisamy',sysdate,'dpalanisamy',sysdate);


INSERT into TICKET_HISTORY (TICKET_HISTORY_ID,TICKET_ID,DATE_FROM,DATE_TO,STATUS_NAME,PRIORITY_LEVEL,ASSIGNED_TO,UN_ASSIGNED,TICKET_TYPE,ACTIVTIY_DONE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fb70550a9f8001550aa849c80003',146476264356641,sysdate,sysdate,'Ticket Created','P1',null,'1','PROACTIVE','Ticket has been Created by dpalanisamy. ','dpalanisamy',sysdate,'dpalanisamy',sysdate);
INSERT into TICKET_HISTORY (TICKET_HISTORY_ID,TICKET_ID,DATE_FROM,DATE_TO,STATUS_NAME,PRIORITY_LEVEL,ASSIGNED_TO,UN_ASSIGNED,TICKET_TYPE,ACTIVTIY_DONE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fa21550c08fb01550c0977bb0000',146476264356641,sysdate,sysdate,'Ticket Created','P1','nmathur','0','PROACTIVE','Assignment Change FROM UnAssigned TO nmathur. ','nmathur',sysdate,'nmathur',sysdate);
INSERT into TICKET_HISTORY (TICKET_HISTORY_ID,TICKET_ID,DATE_FROM,DATE_TO,STATUS_NAME,PRIORITY_LEVEL,ASSIGNED_TO,UN_ASSIGNED,TICKET_TYPE,ACTIVTIY_DONE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fa21550c08fb01550c0977bb0001',146476310043294,sysdate,null,'Ticket Created','P1',null,'1','PROACTIVE','Ticket has been Created by testuser. ','testuser',sysdate,'testuser',sysdate);
INSERT into TICKET_HISTORY (TICKET_HISTORY_ID,TICKET_ID,DATE_FROM,DATE_TO,STATUS_NAME,PRIORITY_LEVEL,ASSIGNED_TO,UN_ASSIGNED,TICKET_TYPE,ACTIVTIY_DONE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fa21550c08fb01550c0977bb0002',146476310043495,sysdate,null,'Ticket Created','P1',null,'1','PROACTIVE','Ticket has been Created by testuser. ','testuser',sysdate,'testuser',sysdate);


INSERT into TICKET_ATTACHMENT (ATTACHMENT_ID,TICKET_ID,FILE_NAME,FILE_TYPE,ATTACHMENT_DATA,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fa21550bbca501550bbe27090000',146476264356641,'test.txt','TXT',load_file('src/test/resources/test.txt'),'dpalanisamy',sysdate,'dpalanisamy',sysdate);
INSERT into TICKET_ATTACHMENT (ATTACHMENT_ID,TICKET_ID,FILE_NAME,FILE_TYPE,ATTACHMENT_DATA,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fa21550bbee001550bbf2bb50000',146476264356641,'test.txt','TXT',load_file('src/test/resources/test.txt'),'dpalanisamy',sysdate,'dpalanisamy',sysdate);
INSERT into TICKET_ATTACHMENT (ATTACHMENT_ID,TICKET_ID,FILE_NAME,FILE_TYPE,ATTACHMENT_DATA,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fa21550bfd7201550bfe37160000',146476264356641,'test.txt','TXT',load_file('src/test/resources/test.txt'),'testuser',sysdate,'testuser',sysdate);
INSERT into TICKET_ATTACHMENT (ATTACHMENT_ID,TICKET_ID,FILE_NAME,FILE_TYPE,ATTACHMENT_DATA,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fa21550be37901550be697090000',146494401026689,'test.txt','TXT',load_file('src/test/resources/test.txt'),'testuser',sysdate,'testuser',sysdate);
INSERT into TICKET_ATTACHMENT (ATTACHMENT_ID,TICKET_ID,FILE_NAME,FILE_TYPE,ATTACHMENT_DATA,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fa59556d62bf01556d66a70f0000',146476310043495,'test.txt','TXT',load_file('src/test/resources/test.txt'),'testuser',sysdate,'testuser',sysdate);
INSERT into TICKET_ATTACHMENT (ATTACHMENT_ID,TICKET_ID,FILE_NAME,FILE_TYPE,ATTACHMENT_DATA,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fa59556d62bf01556d66b60f0000',146476310043494,'test.txt','TXT',load_file('src/test/resources/test.txt'),'testuser',sysdate,'testuser',sysdate);
INSERT into TICKET_ATTACHMENT (ATTACHMENT_ID,TICKET_ID,FILE_NAME,FILE_TYPE,ATTACHMENT_DATA,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('2c90fa59556d62bf01556d77b60f0000',146476310043495,'test.txt','TXT',load_file('src/test/resources/test.txt'),'testuser1',sysdate,'testuser',sysdate);




insert into PROVIDER_DOMAIN 
VALUES('2c90fa21550bbca501550bbe27090003','PROVIDER1','BOFA.SCR',sysdate,sysdate,sysdate,sysdate);
insert into PROVIDER_DOMAIN 
VALUES('dad2d885-11df-466e-9191-6ffc8530ef55','72555616-45ea-4ea0-942b-26af17e12945','BOFA.SCR',sysdate,sysdate,sysdate,sysdate);
insert into PROVIDER_DOMAIN 
VALUES('dad2d885-11df-466e-9191-6ffc8530ef54','72555616-45ea-4ea0-942b-26af17e12946','BOFA.SCR','kvenkateswaran',sysdate,'kvenkateswaran',sysdate);

insert into HTML_KEY 
VALUES('2c90fa21550bbca501550bbe27090032','provider1','type1',1,'attr1','attrVal1','abc','jobA',sysdate,sysdate,sysdate,sysdate);
insert into HTML_KEY 
VALUES('2c90fa21550bbca501550bbe27090033','72555616-45ea-4ea0-942b-26af17e12945','type1',1,'attr1','attrVal1','abc','jobA','kvenkateswaran',sysdate,'kvenkateswaran',sysdate);
insert into HTML_KEY 
VALUES('2c90fa21550bbca501550bbe27090034','72555616-45ea-4ea0-942b-26af17e12946','type2',1,'attr2','attrVal2','abc','jobA','kvenkateswaran',sysdate,'kvenkateswaran',sysdate);


INSERT into OPEN_DOMAIN (DOMAIN_ID,NAME,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('6fa4c78f-d753-4771-ba75-af0d90b43f9b','test','sreddy8',sysdate,'sreddy8',sysdate);
INSERT into OPEN_DOMAIN (DOMAIN_ID,NAME,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('cc2f727c-d3f9-4ff1-bc07-b66d83942b5d','test2','sreddy8',sysdate,'sreddy8',sysdate);
INSERT into OPEN_DOMAIN (DOMAIN_ID,NAME,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('cc2f727c-d3f9-4ff1-bc07-b66d83942b5c','test3','kvenkateswaran',sysdate,'kvenkateswaran',sysdate);


INSERT into PROFILE (PROFILE_ID,PROVIDER_ID,PROFILE_TYPE,FILTER_TYPE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('97d8a2d1-0560-405b-86ec-8d42153ec374','test','Exclusive','Request','sreddy8',sysdate,'sreddy8',sysdate);
INSERT into PROFILE (PROFILE_ID,PROVIDER_ID,PROFILE_TYPE,FILTER_TYPE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('6fa4c78f-d753-4771-ba75-af0d90b43f9b','test','Exclusive','Request','sreddy8',sysdate,'sreddy8',sysdate);
insert into PROFILE (PROFILE_ID,PROVIDER_ID,PROFILE_TYPE,FILTER_TYPE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) 
values ('cc2f727c-d3f9-4ff1-bc07-b66d83942b5d','72555616-45ea-4ea0-942b-26af17e12945','Inclusive','Request','dpalanisamy',sysdate,'dpalanisamy',sysdate);


INSERT into RULES (RULE_ID,FIELD_VALUE,FILTER_TYPE,PROVIDER_ID,PROFILE_ID,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('6fa4c78f-d753-4771-ba75-af0d90b43f9b','test','Request','test','97d8a2d1-0560-405b-86ec-8d42153ec374','sreddy8',sysdate,'sreddy8',sysdate);
INSERT into RULES (RULE_ID,FIELD_VALUE,FILTER_TYPE,PROVIDER_ID,PROFILE_ID,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('6fa4c78f-d753-4771-ba75-af0d90b43f9c','test1','Request','test1','cc2f727c-d3f9-4ff1-bc07-b66d83942b5d','dpalansiamy',sysdate,'dpalansiamy',sysdate);
INSERT into RULES (RULE_ID,FIELD_VALUE,FILTER_TYPE,PROVIDER_ID,PROFILE_ID,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('6fa4c78f-d753-4771-ba75-af0d90b43f9e','test2','Request','test1','cc2f727c-d3f9-4ff1-bc07-b66d83942b5d','kvenkateswaran',sysdate,'kvenkateswaran',sysdate);

insert into WRAPPER_CLASS (WRAPPER_ID,WRAPPER_TYPE,WRAPPER_NAME,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) values ('2c90fa21550bbca501550bbe27090000','CREDENTIALS_SUBSTITUTION_FILTER','CREDENTIAL_WRAPPER','smodgil',sysdate,'smodgil',sysdate);
insert into WRAPPER_CLASS (WRAPPER_ID,WRAPPER_TYPE,WRAPPER_NAME,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) values ('9kodfa21550bbca501550bbe27090000','CREDENTIALS_SUBSTITUTION_FILTER','CREDENTIAL_WRAPPER1','smodgil',sysdate,'smodgil',sysdate);

INSERT into PROVIDER_WRAPPER_MAPPING (PROVIDER_WRAPPER_ID,PROVIDER_ID,WRAPPER_TYPE,WRAPPER_NAME,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) values ('9kodfa21550bbca501550bbe27098798','10000','DATA_MASKING_FILTER','MASKING_FILTER1','smodgil',sysdate,'smodgil',sysdate);
INSERT into PROVIDER_WRAPPER_MAPPING (PROVIDER_WRAPPER_ID,PROVIDER_ID,WRAPPER_TYPE,WRAPPER_NAME,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) values ('6hhjh91550bbca501550bbe270987981','10000','CREDENTIALS_SUBSTITUTION_FILTER','CREDENTIAL_WRAPPER','smodgil',sysdate,'smodgil',sysdate);
INSERT into PROVIDER_WRAPPER_MAPPING (PROVIDER_WRAPPER_ID,PROVIDER_ID,WRAPPER_TYPE,WRAPPER_NAME,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) values ('6hhjh91550bbca501550bbe270987980','10000','CREDENTIALS_SUBSTITUTION_FILTER1','CREDENTIAL_WRAPPER1','kvenkateswaran',sysdate,'kvenkateswaran',sysdate);
INSERT into PROVIDER_WRAPPER_MAPPING (PROVIDER_WRAPPER_ID,PROVIDER_ID,WRAPPER_TYPE,WRAPPER_NAME,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT) values ('6hhjh91550bbca501550bbe270987982','10000','CREDENTIALS_SUBSTITUTION_FILTER2','CREDENTIAL_WRAPPER2','kvenkateswaran',sysdate,'kvenkateswaran',sysdate);


INSERT into JSON_FILTER (FILTER_ID,PROVIDER_ID,IS_MASKED,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('cb3cc7e3-665e-4b38-bc3c-dc7008910d13','6efdb6a4-60a0-4855-a320-259b2b98c65a','Y','dpalansiamy',sysdate,'dpalansiamy',sysdate);
INSERT into JSON_FILTER (FILTER_ID,PROVIDER_ID,IS_MASKED,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('cb3cc7e3-665e-4b38-bc3c-dc7008910d12','6efdb6a4-60a0-4855-a320-259b2b98c65b','Y','kvenkateswaran',sysdate,'kvenkateswaran',sysdate);
INSERT into JSON_FILTER (FILTER_ID,PROVIDER_ID,IS_MASKED,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('cb3cc7e3-665e-4b38-bc3c-dc7008910d11','6efdb6a4-60a0-4855-a320-259b2b98c65c','Y','kvenkateswaran',sysdate,'kvenkateswaran',sysdate);


INSERT into JSON_RULES (RULE_ID, FILTER_ID,FIELD_VALUE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('182c2014-259a-46ae-a22e-f1d3ed714994','cb3cc7e3-665e-4b38-bc3c-dc7008910d13','index','dpalansiamy',sysdate,'dpalansiamy',sysdate);
INSERT into JSON_RULES (RULE_ID, FILTER_ID,FIELD_VALUE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('182c2014-259a-46ae-a22e-f1d3ed714993','cb3cc7e3-665e-4b38-bc3c-dc7008910d13','pagenum','dpalansiamy',sysdate,'dpalansiamy',sysdate);
INSERT into JSON_RULES (RULE_ID, FILTER_ID,FIELD_VALUE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('182c2014-259a-46ae-a22e-f1d3ed714992','cb3cc7e3-665e-4b38-bc3c-dc7008910d12','index','kvenkateswaran',sysdate,'kvenkateswaran',sysdate);
INSERT into JSON_RULES (RULE_ID, FILTER_ID,FIELD_VALUE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('182c2014-259a-46ae-a22e-f1d3ed714991','cb3cc7e3-665e-4b38-bc3c-dc7008910d11','index','kvenkateswaran',sysdate,'kvenkateswaran',sysdate);
INSERT into JSON_RULES (RULE_ID, FILTER_ID,FIELD_VALUE,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT)
values ('182c2014-259a-46ae-a22e-f1d3ed714990','cb3cc7e3-665e-4b38-bc3c-dc7008910d11','index','kvenkateswaran',sysdate,'kvenkateswaran',sysdate);


COMMIT;

