package com.example.gmailapp.data.mapper

import com.example.gmailapp.core.funtational.mapOrDefault
import com.example.gmailapp.core.funtational.orDefault
import com.example.gmailapp.core.mapper.ResultMapper
import com.example.gmailapp.data.dto.emaildetail.EmailDetailsDto
import com.example.gmailapp.data.dto.emaildetail.RecipientInfo
import com.example.gmailapp.data.dto.emaildetail.SenderInfo
import com.example.gmailapp.domain.model.emaildetail.EmailDetailsModel
import com.example.gmailapp.domain.model.emaildetail.FileInfo
import com.example.gmailapp.domain.model.emaildetail.RecipientModel
import com.example.gmailapp.domain.model.emaildetail.SenderInfoModel
import javax.inject.Inject

class EmailDetailsMapper @Inject constructor() :
    ResultMapper<ArrayList<EmailDetailsDto>, EmailDetailsModel> {
    override fun map(input: ArrayList<EmailDetailsDto>): EmailDetailsModel =
        input.first().toEmailDetailsModel()

    private fun EmailDetailsDto.toEmailDetailsModel(): EmailDetailsModel {
        return EmailDetailsModel(
            id = id,
            from = payload.senderInfo.toSenderInfoModel(),
            to = payload.to.toRecipientModel(),
            cc = payload.cc.toRecipientModel(),
            bcc = payload.bcc.toRecipientModel(),
            subject = payload.subject.orEmpty(),
            htmlBody = body?.html ?: body?.text ?: "",
            date = payload.date.orEmpty(),
            isImportant = isImportant.orDefault(),
            isStarred = labels.contains("Starred"),
            isPromotional = isPromotional.orDefault(),
            fileInfo =
                payload.attachments.mapOrDefault(emptyList()) {
                    FileInfo(
                        filename = it!!.filename.orEmpty(),
                        mimeType = it.mimeType.orEmpty(),
                        size = it.size ?: 0L,
                        downLoadUrl = it.downloadUrl
                    )
                },
            labels =
                labels.mapOrDefault(emptyList()) {
                    it!!
                }
        )
    }

    private fun SenderInfo.toSenderInfoModel(): SenderInfoModel {
        return SenderInfoModel(
            email = email,
            name = name,
            profileImage = profileImage
        )
    }

    private fun List<RecipientInfo>?.toRecipientModel(): List<RecipientModel> {
        return this.mapOrDefault {
            RecipientModel(
                email = it.email,
                name = it.name
            )
        }
    }
}